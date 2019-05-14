package main.clients;

import main.utilities.Transaction;
import main.accounts.AccountType;
import main.accounts.BankAccount;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A main user class.
 */
public class User extends BankClient implements Serializable {
    /**
     * A list of user's bank accounts
     */
    private ArrayList<BankAccount> accounts;
    /**
     * A list containing user's recent transactions
     */
    private ArrayList<Transaction> transactionLedger;
    /**
     * An instance of user request containing user account creation info
     */
    private UserRequest userRequest;
    /**
     * A copy of client manager
     */
    private ClientManager clientManager;

    /**
     * Construct a new main.user with username username and password password
     *
     * @param username the username of main.user
     * @param password the password of main.user
     */
    public User(String username, String password) {
        super(username, password);
        accounts = new ArrayList<>();
        transactionLedger = new ArrayList<Transaction>();
        clientManager = ClientManager.getInstance();
    }

    /**
     * Add to a user's list of recent transactions
     *
     * @param transactionType the type of transaction being made
     * @param accountTo       the receiving account
     * @param accountFrom     the sending account
     * @param amount          the amount in the transaction
     */
    public void addTransaction(String transactionType, BankAccount accountTo, BankAccount accountFrom, float amount) {
        transactionLedger.add(new Transaction(transactionType, accountTo, accountFrom, amount));
    }

    /**
     * Add to a user's list of recent transactions
     *
     * @param transactionType the type of transaction being made
     * @param account         the bnk account associated with the transfer
     * @param amount          the amount in the transaction
     */
    public void addTransaction(String transactionType, BankAccount account, float amount) {
        transactionLedger.add(new Transaction(transactionType, account, amount));
    }

    /**
     * Getter for the transaction list
     *
     * @return the transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactionLedger;
    }

    /**
     * Adds an account to the current user's list
     *
     * @param account the account type to add
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Getter for the account list
     *
     * @return the bank accounts
     */
    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    /**
     * Request a new single, domestic account with specified account type
     *
     * @param accountType the desired account type
     */
    public void requestNewAccount(AccountType accountType) {
        clientManager.addToAccountRequestList(new UserRequest(accountType, this));
    }

    /**
     * Request a new single overseas account with specified account type
     *
     * @param accountType the desired account type
     * @param currType    the desired currency type
     */
    public void requestNewAccount(AccountType accountType, String currType) {
        UserRequest userRequest = new UserRequest(accountType, this);
        userRequest.currType = currType;
        clientManager.addToAccountRequestList(userRequest);
    }

    /**
     * Request a new joint account with specified account type
     *
     * @param accountType the desired account type
     * @param other       the user that is sharing the account
     */
    public void requestNewAccount(AccountType accountType, User other) {
        clientManager.addToAccountRequestList(new UserRequest(accountType, this, other));
    }


    /**
     * Getter for the user request
     *
     * @return the current request
     */
    public UserRequest getUserRequest() {
        return userRequest;
    }
}
