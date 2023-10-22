package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;

import com.aninfo.repository.TransactionRepository;
import com.aninfo.service.AccountService;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    private Double valueOfProm(Double amount){
        Double promoAmount = 0.0;
        if(amount >= 2000){
            return (amount * 0.1) > 500 ? 500 : amount * 0.1;
        }
        return promoAmount;
    }

    private Transaction deposit(Account accountAssociated, Transaction transaction){
        if(transaction.getValueOfTransaction() <= 0){
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        accountAssociated.setBalance(accountAssociated.getBalance() + transaction.getValueOfTransaction() + valueOfProm(transaction.getValueOfTransaction()));
        return transaction;
    }
    private Transaction withdraw(Account accountAssociated, Transaction transaction){
        if (accountAssociated.getBalance() < transaction.getValueOfTransaction()){
            throw new InsufficientFundsException("Insufficient funds");
        }
        accountAssociated.setBalance(accountAssociated.getBalance() - transaction.getValueOfTransaction());
        return transaction;
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Account accountAssociated, String type, Double amount){
        Transaction transaction = new Transaction(accountAssociated.getCbu(), type, amount);
        if("Deposit".equals(transaction.getTypeTransaction())){
            deposit(accountAssociated, transaction);
        } else if ("Withdraw".equals(transaction.getTypeTransaction())) {
            withdraw(accountAssociated, transaction);
        } else {
            throw new InvalidTransactionTypeException("Invalid type transaction");
        }
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactionByCbu(Long cbu) {
        return transactionRepository.findTransactionByAccountCbu(cbu);
    }

    public void deleteById(Long cbu) {
        transactionRepository.deleteById(cbu);
    }


}
