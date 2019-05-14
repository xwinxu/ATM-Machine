package main.utilities;

import main.accounts.BankAccount;
import main.clients.User;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Transaction class representing an ATM transaction.
 */
public class Transaction implements Serializable {
    /**
     * The type of the Transaction
     */
    private String transactionType;
    /**
     * The account to which the money is going to.
     */
    private BankAccount accountTo;
    /**
     * The account from which the money originates from.
     */
    private BankAccount accountFrom;
    /**
     * The date on which Transaction was made
     */
    private LocalDate date;
    /**
     * Represents if a Transaction is yet to be undone.
     */
    public boolean hasUndo;
    /**
     * The amount of money the Transaction involved.
     */
    private float amount;

    /**
     * Creates a new Transaction.
     *
     * @param transactionType The type of Transaction.
     * @param accountTo The account to which the money is going to.
     * @param accountFrom The account from which the money originates from.
     * @param amount The amount of money the Transaction involved.
     */
    public Transaction(String transactionType, BankAccount accountTo, BankAccount accountFrom, float amount) {
        this.transactionType = transactionType;
        this.accountTo = accountTo;
        this.accountFrom = accountFrom;
        this.date = LocalDate.now();
        this.amount = amount;
    }

    /**
     * Creates a new Transaction
     *
     * @param transactionType The type of Transaction.
     * @param account The account involved in this transaction.
     * @param amount The amount of money the Transaction involves.
     */
    public Transaction(String transactionType, BankAccount account, float amount) {
        this.transactionType = transactionType;
        System.out.println(transactionType);
        if (transactionType.equals("Withdraw") ||
                transactionType.equals("Transfer - Bill Payment") || transactionType.equals("Withdraw(UNDO)")) {
            this.accountFrom = account;
        } else if (transactionType.equals("Deposit") || transactionType.equals("Deposit(UNDO)")) {
            this.accountTo = account;
        }
        this.date = LocalDate.now();
        this.amount = amount;
    }

    /**
     * Undoes Transaction
     *
     * @param user the user's transaction from which we want to revert the transaction
     *
     * @return represents whether the transaction was successfully undone
     */
    public boolean undoTransaction(User user) {
        hasUndo = true;
        switch (transactionType) {
            case "Deposit":
                if (accountTo.canWithdraw(amount)) {
                    accountTo.withdraw(amount);
                    user.addTransaction("Withdraw(UNDO)", this.accountTo, amount);
                    System.out.println(this.accountTo);
                    System.out.println(user.getTransactions().get(0).accountTo);
                    return true;
                } else{return false;}
            case "Withdraw":
                accountFrom.deposit(amount);
                user.addTransaction("Deposit(UNDO)", accountFrom, amount);
                return true;
            case "Transfer":
                if (accountTo.canWithdraw(amount)){
                    accountTo.transfer(accountFrom, amount);
                    user.addTransaction("Transfer(UNDO)", accountFrom, accountTo, amount);
                    return true;
                } else {return false;}
            case "Transfer - Bill Payment":
                accountFrom.deposit(amount);
                user.addTransaction("Deposit (UNDO)", accountFrom, amount);
                return true;
            default:
                return false;
        }
    }

    /**
     * Returns a String representation of Transaction object including the type, accounts involved, amount and date.
     *
     * @return a String representation of Transaction
     */
    @Override
    public String toString() {

        if ((transactionType.equalsIgnoreCase("Withdraw")) ||
                (transactionType.equalsIgnoreCase("Withdraw(Undo)")) ||
                (transactionType.equalsIgnoreCase("Transfer - Bill Payment"))) {
            return "Transaction type: " + transactionType + "\n" + "Receiving Account:--- " + "\n"
                    + "Sending Account: " + accountFrom.toString() + "\n" + "Amount: " + amount + " on " + date;
        } else if ((transactionType.equalsIgnoreCase("Deposit")) ||
                (transactionType.equalsIgnoreCase("Deposit(Undo)"))) {
            return "Transaction type: " + transactionType + "\n" + "Receiving Account: " + accountTo.toString() + "\n"
                    + "Sending Account:---" + "\n" + "Amount: " + amount + " on " + date;
        } else {
            return "Transaction type: " + transactionType + "\n" + "Receiving Account: " + accountTo.toString() + "\n"
                    + "Sending Account: " + accountFrom.toString() + "\n" + "Amount: " + amount + " on " + date;
        }
    }
}

