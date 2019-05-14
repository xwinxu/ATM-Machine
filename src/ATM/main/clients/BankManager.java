package main.clients;

import main.accounts.CreditCard;
import main.accounts.SavingsAccount;
import main.utilities.Transaction;
import main.accounts.AccountFactory;
import main.accounts.BankAccount;
import main.utilities.InputChecker;
import main.utilities.KeyValueFileHandler;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A BankManger class containing all manager methods
 */
public class BankManager extends BankClient implements Employee, Serializable {
    /**
     * File names for txt files used in bank manager
     */
    private static final String CONFIG_FILENAME = "config.txt";
    private static final String CASH_INVENTORY_FILENAME = "cash_inventory.txt";
    private static final int DEFAULT_AMOUNT = 50;
    /**
     * An AccountFactory for creating new accounts
     */
    private AccountFactory accountFactory;
    /**
     * A file handler that maps key to value
     */
    private KeyValueFileHandler handler;

    /*
    The client manager singleton
     */
    private ClientManager clientManager = ClientManager.getInstance();

    /**
     * Construct a new instance of a bank manager
     *
     * @param username manager's username
     * @param password manager's password
     */
    public BankManager(String username, String password) {
        super(username, password);
        accountFactory = new AccountFactory();
        handler = new KeyValueFileHandler();
    }

    /**
     * Create and initialize a BankAccount for given user and set user's initial password
     *
     * @param userRequest A UserRequest class containing user's info that is used to create his/her account
     */
    @Override
    public void registerAccount(UserRequest userRequest) {
        if (userRequest.isJoint()) {
            accountFactory.getAccount(userRequest.getRequestType(),
                    userRequest.getUser(), userRequest.getOther());
        } else if (userRequest.overSeas) {
            accountFactory.getAccount(userRequest.getRequestType(), userRequest.getUser(), userRequest.currType);
        } else {
            accountFactory.getAccount(userRequest.getRequestType(), userRequest.getUser());
        }
    }

    /**
     * Registers a user by adding it to Master list
     *
     * @param user a user to register
     */
    public void registerUser(User user) {
        if (!clientManager.getUserList().contains(user)) {
            clientManager.addToMasterList("user", user);
            System.out.println(clientManager.getUserList());
        }
    }

    /**
     * Set current system date to current date if not given specified date
     */
    public void setDate() {
        setDate(LocalDate.now());
    }

    /**
     * Set current system date to specified date
     *
     * @param date date to set system to
     */
    public void setDate(LocalDate date) {
        this.handler.setFileValue(CONFIG_FILENAME, "date", date);
        applyInterest(date);
    }

    /**
     * applying interest to each user's account every first day of each month
     */
    public void applyInterest(LocalDate localDate) {
        System.out.println("apply interest ");
        if (localDate.getDayOfMonth() == 1) {
            for (BankClient u : clientManager.getUserList()) {
                User user = (User) u;
                ArrayList<BankAccount> accountList = user.getAccounts();
                for (BankAccount account : accountList) {
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).accumulateInterest();
                        System.out.println("SAVING GOT INTEREST");
                    } else if (account instanceof CreditCard) {
                        ((CreditCard) account).accumulateInterest();
                        System.out.println("CREDIT GOT INTEREST");
                    }
                }
            }
        }
    }

    /**
     * Restock ATM machine with more currencies
     *
     * @param currency the type of currency that wish to be restocked
     * @param amount   the amount of the currency restocked
     */
    public void restock(String currency, int amount) {
        int existingCash = handler.getFileIntValue(CASH_INVENTORY_FILENAME, currency);
        handler.setFileValue(CASH_INVENTORY_FILENAME, currency, existingCash + amount);
    }

    /**
     * Get the current amount of requested currency
     *
     * @param currency the type of currency to check
     * @return the amount left in the ATM
     */

    public int getCurrencyAmount(String currency) {
        return handler.getFileIntValue(CASH_INVENTORY_FILENAME, currency);
    }

    /**
     * Undo the latest transaction for given account
     *
     * @param user the account of which the transaction will be undone
     * @return return true if undo last transaction successfully
     */
    public boolean undoLastTransaction(User user) {
        if (user.getTransactions().isEmpty()) {
            return false;
        }
        int lastIndex = user.getTransactions().size() - 1;
        return user.getTransactions().get(lastIndex).undoTransaction(user);
    }

    /**
     * Undoes a recent transaction by a user
     *
     * @param user the user whose transaction to undo
     * @return whether undo was successful
     */
    public boolean undoSelectTransaction(User user) {
        ArrayList<Transaction> options = user.getTransactions();
        if (options.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < options.size(); i++) {
                System.out.println(options.get(i) + " enter \"" + (i + 1) + "\".");
            }
            int input = new InputChecker().inputInt();
            return user.getTransactions().get(input - 1).undoTransaction(user);
        }
    }

    /**
     * Promotes a teller
     *
     * @param teller the teller to promote
     */
    public void promotePendingTeller(BankTeller teller) {
        clientManager.addToMasterList("manager", new BankManager(teller.getUsername(), teller.getPassword()));
        clientManager.removeFromMasterList("teller", teller);
    }
}