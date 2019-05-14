package main.accounts;

import main.clients.User;

import java.io.Serializable;

/**
 * A LineOfCredit debt bank account class.
 */
public class LineOfCredit extends BankAccount implements Serializable {

    /**
     * Credit limit for LineOfCredit account.
     */
    private int limit = 10000;

    /**
     * Construct a new LineOfCredit account.
     *
     * @param user the account holder user.
     */
    public LineOfCredit(User user) {
        super(user);
        setIsDebt(false);
    }

    /**
     * Construct a new LineOfCredit account.
     *
     * @param user1 the first account holder
     * @param user2 the second account holder
     */
    public LineOfCredit(User user1, User user2) {
        super(user1, user2);
        setIsDebt(false);
    }

    /**
     * Deposits a given sum into LineOfCredit account balance.
     *
     * @param amount the amount to be deposited.
     */
    public void deposit(float amount) {
        balance -= amount;
    }

    /**
     * String representing for printing purposes
     * @return the string representation of LineOfCredit account object
     */
    public String toString() {
        return "\"" + accountID + "\"";
    }

    /**
     * Withdraw a given amount from LineOfCredit account balance.
     *
     * @param amount the amount to be withdrawn.
     */
    public void withdraw(float amount) {
        if (canWithdraw(amount)) {
            balance += amount;
        }
    }

    /**
     * Checks if LineOfCredit account has the ability to withdraw.
     *
     * @param amount the amount to be withdrawn.
     * @return whether there is enough balance in the SavingsAccount to transfer amount.
     */
    public boolean canWithdraw(float amount) {
        return amount + balance <= limit;
    }
}
