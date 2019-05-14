package main.accounts;

import main.clients.User;

import java.io.Serializable;

/**
 * A CreditCard debt bank account class.
 */
public class CreditCard extends BankAccount implements Serializable {

    /**
     * Credit limit for CreditCard account.
     */
    int limit = 5000;

    /**
     * Construct a new CreditCard account.
     *
     * @param user the account holder user.
     */
    public CreditCard(User user) {
        super(user);
        setIsDebt(false);
    }

    /**
     * Construct a new CreditCard account.
     *
     * @param user1 the first account holder
     * @param user2 the second account holder
     */
    public CreditCard(User user1, User user2) {
        super(user1, user2);
        setIsDebt(false);
    }


    /**
     * A setter for the limit of the CreditCard account
     *
     * @param limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Deposits a given sum into CreditCard account balance.
     *
     * @param amount the amount to be deposited.
     */
    public void deposit(float amount) {
        balance -= amount;
    }


    /**
     * String representing for printing purposes
     *
     * @return the string representation of CreditCard account object
     */
    public String toString() {
        return "\"" + accountID + "\"";
    }

    /**
     * Withdraw a given amount from CreditCard account balance.
     *
     * @param amount the amount to be withdrawn.
     */
    public void withdraw(float amount) {
        if (canWithdraw(amount)) {
            balance += amount;
        }
    }

    /**
     * Checks if CreditCard account has the ability to withdraw.
     *
     * @param amount the amount to be withdrawn.
     * @return whether there is enough balance in the CreditCard account to transfer amount.
     */
    public boolean canWithdraw(float amount) {
        return amount + balance <= limit;
    }


    /**
     * Notifies the user they cannot transfer from a CreditCard account
     *
     * @param other  the receiving BankAccount.
     * @param sum the amount of money to be transferred
     * @return -1, indicating a failed transfer
     */
    @Override
    public int transfer(BankAccount other, float sum) {
        System.out.println("Cannot transfer out from credit card account.");
        return -1;
    }

    /**
     * To add interest to an account at the start of each month
     */
    public void accumulateInterest() {
        balance = (float) (balance * 1.02);
    }
}
