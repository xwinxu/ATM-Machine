package main.accounts;

import main.clients.User;

import java.io.Serializable;


/**
 * A factory for creating accounts
 */
public class AccountFactory implements Serializable {
    /**
     * creates an account of the specified type for given user
     *
     * @param accountType the type of BankAccount that is being created
     * @param user        the account for whom this account is created for
     * @return a new BankAccount for the user
     */
    public BankAccount getAccount(AccountType accountType, User user) {

        switch (accountType) {
            case CREDIT:
                return new CreditCard(user);
            case LINE_CREDIT:
                return new LineOfCredit(user);
            case CHEQUING:
                return new ChequingAccount(user);
            case SAVINGS:
                return new SavingsAccount(user);
            case OVERSEAS:
                return new OverseasAccount(user);
            default:
                return null;
        }
    }

    /**
     * Creates a new Overseas account
     * @param accountType the type of account this will be
     * @param user the user to which this account belongs to
     * @param currency the currency in which this account will operate
     * @return a new Overseas account that belongs to user
     */
    public BankAccount getAccount(AccountType accountType, User user, String currency){
        OverseasAccount overseasAccount = new OverseasAccount(user);
        overseasAccount.setCurrency(currency);
        return overseasAccount;
    }

    /**
     * Creates a new BankAccount of type accountType
     * @param accountType the type of account this will be
     * @param user the user to which this account will belong to
     * @param other the second user to which this account will belong to
     * @return a new BankAccount
     */
    public BankAccount getAccount(AccountType accountType, User user, User other) {

        switch (accountType) {
            case CREDIT:
                return new CreditCard(user,other);
            case LINE_CREDIT:
                return new LineOfCredit(user,other);
            case CHEQUING:
                return new ChequingAccount(user,other);
            case SAVINGS:
                return new SavingsAccount(user,other);
            case OVERSEAS:
                return new OverseasAccount(user,other);
            default:
                return null;
        }
    }

}
