package main.accounts;

import main.clients.User;

import java.io.Serializable;

/**
 * A SavingsAccount asset bank account class.
 */
public class SavingsAccount extends BankAccount implements Serializable {

    /**
     * Construct a new SavingsAccount.
     *
     * @param user the account holder user.
     */
    public SavingsAccount(User user) {
        super(user);
        setIsDebt(true);
    }


    /**
     * Construct a new SavingsAccount.
     *
     * @param user1 the first account holder
     * @param user2 the second account holder
     */
    public SavingsAccount(User user1, User user2) {
        super(user1, user2);
        setIsDebt(true);
    }

    /**
     * Withdraw a given amount from SavingsAccount balance.
     *
     * @param amount the amount to be withdrawn.
     */
    public void withdraw(float amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
        }
    }


    /**
     * String representing for printing purposes
     *
     * @return the string representation of Savings account object
     */
    public String toString() {
        return "\"" + accountID + "\"";
    }

    /**
     * Deposits a given sum into SavingsAccount balance.
     *
     * @param sum the amount to be deposited.
     */
    public void deposit(float sum) {
        balance += sum;
    }

    /**
     * Checks if SavingsAccount has the ability to withdraw.
     *
     * @param amount the amount to be withdrawn.
     * @return whether there is enough balance in the SavingsAccount to transfer amount.
     */
    public boolean canWithdraw(float amount) {
        return amount <= balance;
    }

    /**
     * To add interest to an account at the start of each month
     */
    public void accumulateInterest() {
        balance = (float) (balance * 1.001);
    }

}