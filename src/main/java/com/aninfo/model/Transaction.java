package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double valueOfTransaction;
    private String typeTransaction;
    private Long accountCbu;

    public Transaction(){}

    public Transaction(Long cbu, String type, Double amount) {
        this.accountCbu = cbu;
        this.typeTransaction=type;
        this.valueOfTransaction=amount;
    }

    public Double getValueOfTransaction(){
        return valueOfTransaction;
    }

    public String getTypeTransaction(){
        return typeTransaction;
    }

    public Long getId(){
        return id;
    }

    public Long getAccountCbuAssociated(){
        return accountCbu;
    }

    public void saveAccountCbu(Long cbu){
        this.accountCbu = cbu;
    }
}


