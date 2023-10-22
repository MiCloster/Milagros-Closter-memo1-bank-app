Feature: Bank account transactions
  Checking bank account transactions

  Scenario: Successfully creation transactionDeposit
    Given Account with a balance of 1000
    When Trying to TransactionDeposit 500
    Then Transaction value should be 500

  Scenario: Cannot transactionDeposit when sum is negative
    Given Account with a balance of 200
    When Trying to TransactionDeposit -100
    Then Operation should be denied due to negative sum

  Scenario: Cannot transactionDeposit when sum is zero
    Given Account with a balance of 200
    When Trying to TransactionDeposit 0
    Then Operation should be denied due to negative sum

  Scenario: Successfully transactionWithdraw money when balance is enough
    Given Account with a balance of 1000
    When Trying to TransactionWithdraw 500
    Then Transaction value should be 500

  Scenario: Cannot transactionWithdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to TransactionWithdraw 1001
    Then Operation should be denied due to insufficient funds

  Scenario: Cannot enter that type of transaction
    Given Account with a balance of 1000
    When Trying to Transaction Investment 500
    Then Operation should be denied due to invalid type transaction

  Scenario: Successfully enter that type of transaction
    Given Account with a balance of 1000
    When Trying to Transaction Withdraw 500
    Then Transaction value should be 500

  Scenario: Successfully enter that type of transaction deposit
    Given Account with a balance of 1000
    When Trying to Transaction Deposit 500
    Then Transaction value should be 500

