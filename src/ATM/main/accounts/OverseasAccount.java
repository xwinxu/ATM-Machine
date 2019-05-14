package main.accounts;

import main.clients.ClientManager;
import main.utilities.Currency;
import main.utilities.InputChecker;
import main.clients.User;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * An OverseasAccount debt bank account class.
 */

public class OverseasAccount extends BankAccount implements Serializable {
    /**
     * Limit for OverseasAccount account.
     */
    private int limit = 50000;

    /**
     * List of currencies supported
     */
    private static String[] currency_list = {"CAD", "USD", "EUR"};
    private static InputChecker checker = new InputChecker();
    private ClientManager clientManager;

    /**
     * The currency this account carries
     */
    private Currency currency;

    /**
     * Construct a new Overseas account.
     *
     * @param user the account holder user.
     */
    public OverseasAccount(User user) {
        super(user);
        setIsDebt(true);
        clientManager = ClientManager.getInstance();
        this.accountID = "Overseas Account: " + user.getUsername() + (user.getAccounts().size());
        //setCurrency();
    }

    /**
     * Construct a new Overseas account.
     *
     * @param user1 the first account holder
     * @param user2 the second account holder
     */
    public OverseasAccount(User user1, User user2) {
        super(user1, user2);
        setIsDebt(true);
        clientManager = ClientManager.getInstance();
        this.accountID = "Overseas Joint Account: " + user1.getUsername() + user2.getUsername();
        //setCurrency();
    }

    /**
     * A setter for the limit of this Overseas account
     *
     * @param limit the new limit for the Overseas account
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * A setter for the currency of this account
     *
     */
    private void setCurrency(){
        System.out.println("Please select the currency for this account:");

        for (int i = 0; i < currency_list.length; i++) {
            System.out.println(currency_list[i] + " enter \"" + (i + 1) + "\".");
        }
        int input = checker.inputInt();

        this.currency = new Currency(currency_list[input - 1]);
    }

    /**
     * A setter for the currency of this account
     *
     * @param currency the currency in which this account will operate
     */
    public void setCurrency(String currency){
        this.currency = new Currency(currency);
    }


    /**
     * A getter for the currency in which this Overseas account operates in.
     *
     * @return the Currency of this account
     */
    public Currency getCurrency(){
        return this.currency;
    }


    /**
     * Deposits a given sum into OverseasAccount account balance.
     *
     * @param amount the amount to be deposited.
     */
    public void deposit(float amount) {
        amount = amount * (1 / currency.getConversionValue());
        balance += amount;
    }


    /**
     * Returns a string representation of this Overseas bank account and the currency it holds.
     *
     * @return String
     */
    public String toString() {
        return "\"" + accountID + "\"";
    }


    /**
     * Withdraw a given amount from OverseasAccount account balance.
     *
     * @param amount the amount to be withdrawn.
     */
    public void withdraw(float amount) {
        amount = amount * (1 / currency.getConversionValue());
        if (canWithdraw(amount)) { balance -= amount; }
    }

    /**
     * Checks if OverseasAccount account has the ability to withdraw.
     *
     * @param amount the amount to be withdrawn.
     * @return whether there is enough balance in the OverseasAccount to transfer amount.
     */
    public boolean canWithdraw(float amount) {
        return balance - amount >= limit;
    }

    /**
     * Returns a String representation of the balance of this Overseas account with the appropriate currency
     *
     * @return A String representation of the balance, including the currency it is in
     */
    public String getBalance(){
        DecimalFormat twoDecimal = new DecimalFormat("0.00");
        System.out.println(this.getCurrency().getType());
        switch (this.getCurrency().getType()){
            case "USD":
                return "$" + twoDecimal.format(balance) + " (USD)";
            case "CAD":
                return "$" + twoDecimal.format(balance) + " (CAD)";
            case "EUR":
                return "€" + twoDecimal.format(balance) + " (EUR)";
            default:
                return "$" + twoDecimal.format(balance);
        }
    }

}
