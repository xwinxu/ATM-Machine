package main.clients;

import main.accounts.AccountFactory;
import main.accounts.BankAccount;

import java.io.Serializable;

/**
 * A bank teller that has less access at the bank than a BankManager and also a user with their own account(s)
 */
public class BankTeller extends User implements Employee, Serializable {
    /**
     * An account factory for creating new accounts
     */
    private AccountFactory factory;
    /**
     * A copy of client manager
     */
    private ClientManager clientManager;
    /**
     * A bank teller's experience point(s)
     */
    private int EXP_POINTS;
    /**
     * The required experience foe a teller to request for a promotion
     */
    private static final int CUTOFF = 1;

    /**
     * Construct a new bank teller with username username and password password
     *
     * @param username the username of teller
     * @param password the password of teller
     */
    public BankTeller(String username, String password) {
        super(username, password);
        factory = new AccountFactory();
        clientManager = ClientManager.getInstance();
        EXP_POINTS = 0;

    }

    /**
     * Register user's request for new asset accounts
     *
     * @param userRequest information about the user's account creation request
     */
    @Override
    public void registerAccount(UserRequest userRequest) {
        BankAccount newAccount = factory.getAccount(userRequest.getRequestType(), userRequest.getUser());
        if (!userRequest.getUser().getAccounts().contains(newAccount)) {
            userRequest.getUser().addAccount(newAccount);
        }
        EXP_POINTS++;
        System.out.println(EXP_POINTS);
    }

    /**
     * Request for a promotion to a bank manager
     */
    public void promotionRequest() {
        if (EXP_POINTS >= CUTOFF) {     // cutoff is set to 1 so its easier to demonstrate
            clientManager.addToMasterList("pendTeller", this);
            System.out.println("Congratulation! Your request is being processed.");
        } else {
            System.out.println("Insufficient experience, try again in the future.");
        }
    }

    /**
     * Getter for the teller's level of experience
     *
     * @return teller's experience points
     */
    public int getExpPoints() {
        return EXP_POINTS;
    }
}
