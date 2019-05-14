package main.accounts;

import main.clients.User;

import java.io.Serializable;

/**
 * A ChequingAccount asset bank account class.
 */
public class ChequingAccount extends BankAccount implements Serializable {

    /**
     * Construct a new ChequingAccount.
     *
     * @param user the account holder user.
     */
    public ChequingAccount(User user) {
        super(user);
        setIsDebt(true);
    }


    /**
     * Construct a new ChequingAccount.
     *
     * @param user1 the first account holder
     * @param user2 the second account holder
     */
    public ChequingAccount(User user1, User user2) {
        super(user1, user2);
        setIsDebt(true);
    }


    /**
     * Deposits a given sum into ChequingAccount balance.
     *
     * @param sum the amount to be deposited.
     */
    public void deposit(float sum) {
        balance += sum;
    }

    /**
     * Checks if ChequingAccount has the ability to withdraw.
     *
     * @param amount the amount to be withdrawn.
     * @return whether there is enough balance in the SavingsAccount to transfer amount.
     */
    public boolean canWithdraw(float amount) {
        return balance - amount >= -100;
    }

    /**
     * String representing for printing purposes
     * @return the string representation of Chequing Account object
     */
    public String toString() {
        return "\"" + accountID + "\"";
    }

    /**
     * Withdraw a given amount from ChequingAccount balance.
     *
     * @param amount the amount to be withdrawn.
     */
    public void withdraw(float amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
        }
    }
}