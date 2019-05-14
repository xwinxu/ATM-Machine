package main.accounts;

import main.utilities.KeyValueFileHandler;
import main.clients.User;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * A BankAccount abstract class.
 */
public abstract class BankAccount implements Serializable {
    /**
     * Files used by BankAccount
     */
    private static final String CASH_INVENTORY_FILENAME = "cash_inventory.txt";
    private static final String ALERTS_FILENAME = "alerts.txt";
    /**
     * Balance of bank account.
     */
    public float balance;
    /**
     * Identification for account.
     */
    public String accountID;
    /**
     * Account creation date.
     */
    private String creationDate;
    /**
     * The file handler for account transactions.
     */
    private KeyValueFileHandler fileHandler;

    /**
     * Currency of the account.
     */
    private String currencyType;

    /**
     * A boolean representing if this is an asset account of a debt account
     */
    private boolean isDebt;

    /**
     * Construct a new BankAccount.
     *
     * @param user the account holder user.
     */
    public BankAccount(User user) {
        balance = 0;
        user.addAccount(this);
        accountID = setID(user);
        fileHandler = new KeyValueFileHandler();
        if (creationDate == null) {
            creationDate = fileHandler.getFileValue("config.txt", "date");
        }
    }

    /**
     * Construct a new BankAccount.
     *
     * @param user1 the first account holder user.
     * @param user2 the second account holder user.
     */
    public BankAccount(User user1, User user2) {
        balance = 0;
        accountID = "Joint Account " + user1.getUsername() + " and " + user2.getUsername();
        fileHandler = new KeyValueFileHandler();
        if (creationDate == null) {
            creationDate = fileHandler.getFileValue("config.txt", "date");
        }
        user1.addAccount(this);
        user2.addAccount(this);
    }

    /**
     * A setter for the isDebt boolean.
     */
    public void setIsDebt(boolean isDebt){
        this.isDebt = isDebt;
    }

    /**
     * A getter for the isDebt boolean.
     * @return whether this account is a debt account
     */
    public boolean getIsDebt(){
        return isDebt;
    }


    /**
     * Returns the creation date for bank account.
     *
     * @return creation date as a LocalDate.
     */
    public String getCreationDate() {
        return creationDate;
    }


    /**
     * A setter for the ID of an account
     * @param user the user to which this account belongs to
     * @return a String of the new accountID
     */
    private String setID(User user) {
        String type;
        if (this instanceof ChequingAccount) { type = "Chequing Account "; }
        else if (this instanceof CreditCard) { type = "Credit Card "; }
        else if (this instanceof LineOfCredit) { type = "Line Of Credit "; }
        else if (this instanceof OverseasAccount) { type = "Overseas Account "; }
        else if (this instanceof SavingsAccount) { type = "Savings Account "; }
        else { type = "Bank Account "; }
        return type + user.getUsername() + (user.getAccounts().size());
    }


    /**
     * Generates an alert to the Bank Manager when ATM bills are low
     */
    public void updateAlerts() {
        String[] cashes = new String[]{"5", "10", "20", "50"};
        for (String cash : cashes) {
            int amount = fileHandler.getFileIntValue(CASH_INVENTORY_FILENAME, cash);
            if (amount < 20) {
                fileHandler.setFileValue(ALERTS_FILENAME, cash, 20 - amount);
            }
        }
    }

    /**
     * Update the bills in the ATM machine.
     *
     * @param amount an amount that can be made from valid amount of bills (signed int)
     */
    public void updateBills(int amount, String type) {
        // get the current bill amount in ATM machine
        int five = fileHandler.getFileIntValue(CASH_INVENTORY_FILENAME, "5");
        int ten = fileHandler.getFileIntValue(CASH_INVENTORY_FILENAME, "10");
        int twenty = fileHandler.getFileIntValue(CASH_INVENTORY_FILENAME, "20");
        int fifty = fileHandler.getFileIntValue(CASH_INVENTORY_FILENAME, "50");

        int num = amount;
        int num50 = 0;
        int num20 = 0;
        int num10 = 0;
        int num5 = 0;
        if(type.equals("Withdraw")) {
            while (num > 0) {
                if ((num % 50) != num && fifty >= (num / 50)) {
                    num = num % 50;
                } else if (num % 20 != num && twenty >= (num / 20)) {
                    num = num % 20;
                } else if (num % 10 != num && ten >= (num / 10)) {
                    num10 = num / 10;
                    if (ten >= num10) {
                        num = num % 10;
                    }
                } else if (num % 5 != num) {
                    num5 = num / 5;
                    if (five >= num5) {
                        num = num % 5;
                    } else {
                        System.out.println("Sorry, this ATM doesn't have enough bills.");
                        return;
                    }
                }
            }
        }
        if (num == 0) {
            if (type.equals("Withdraw")) {
                five -= num5;
                ten -= num10;
                twenty -= num20;
                fifty -= num50;
            } else if (type.equals("Deposit")) {
                five += num5;
                ten += num10;
                twenty += num20;
                fifty += num50;
            }
            fileHandler.setFileValue(CASH_INVENTORY_FILENAME, "5", five);
            fileHandler.setFileValue(CASH_INVENTORY_FILENAME, "10", ten);
            fileHandler.setFileValue(CASH_INVENTORY_FILENAME, "20", twenty);
            fileHandler.setFileValue(CASH_INVENTORY_FILENAME, "50", fifty);
        }

        updateAlerts();
    }

    /**
     * Returns the balance for bank account.
     *
     * @return balance as a String.
     */
    public String getBalance() {
        DecimalFormat twoDecimal = new DecimalFormat("0.00");
        return "$" + twoDecimal.format(balance);
        //return twoDecimal.format(balance);
    }

    /**
     * Returns the balance for this account
     *
     * @return balance as a double
     */
    public double getBalanceDouble(){
        return balance;
    }

    /**
     * Withdraw a given amount from bank account balance.
     *
     * @param sum the amount to be withdrawn.
     */
    public abstract void withdraw(float sum);

    /**
     * Returns a String representation of the account
     *
     * @return a String representation of the account
     */
    public abstract String toString();

    /**
     * Deposits a given sum into bank account balance.
     *
     * @param sum the amount to be deposited.
     */
    public abstract void deposit(float sum);

    /**
     * Checks if bank account has the ability to withdraw.
     *
     * @param sum the amount to be withdrawn.
     * @return whether there is enough balance in the SavingsAccount to transfer amount.
     */
    public abstract boolean canWithdraw(float sum);


    /**
     * Transfers a given amount from SavingsAccount to a different BankAccount.
     *
     * @param amount the amount to be transferred.
     * @param other  the receiving BankAccount.
     */
    public int transfer(BankAccount other, float amount) {
        if (canWithdraw(amount)) {
            withdraw(amount);
            other.deposit(amount);
            return 0;
        } else {
            return -1;
        }
    }
}
