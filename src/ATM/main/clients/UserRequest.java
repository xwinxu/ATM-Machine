package main.clients;

import main.accounts.AccountType;
import main.clients.User;

import java.io.Serializable;

/**
 * A class to contain and provide user information to create new accounts
 */
public class UserRequest implements Serializable {
    /**
     * The type of account user wants to create
     */
    private AccountType requestType;
    /**
     * The user requesting the account creation
     */
    private User user;
    /**
     * The user sharing the joint account if account is a joint account
     */
    private User other;
    /**
     * True if the account requested is a joint account
     */
    public boolean joint;
    /**
     * True if the account request is an oversea account
     */
    public boolean overSeas = false;
    /**
     * The desired currency type if it is a overseas account
     */
    public String currType;

    /**
     * Create a new instance of user request
     *
     * @param requestType the account type requested
     * @param user        the user requesting the account creation
     */
    public UserRequest(AccountType requestType, User user) {
        this.requestType = requestType;
        this.user = user;
        if (requestType.equals(AccountType.OVERSEAS)) {
            overSeas = true;
        }
    }

    /**
     * Create a new instance of user request
     *
     * @param requestType the account type requested
     * @param user        the user requesting the account creation
     * @param other       the other user sharing the joint acocunt
     */
    public UserRequest(AccountType requestType, User user, User other) {
        this.requestType = requestType;
        this.user = user;
        this.other = other;
        this.joint = true;
    }

    /**
     * The user requesting the account creation
     *
     * @return return the requesting user
     */
    public User getUser() {
        return user;
    }

    /**
     * The other user sharing the account
     *
     * @return the other user requesting for a joint account
     */
    public User getOther() {
        return other;
    }

    /**
     * True if it is a joint account request
     *
     * @return a boolean for whether or not the account request is a joint account
     */
    public boolean isJoint() {
        return joint;
    }

    /**
     * Get the request type
     *
     * @return a String containing the request type
     */
    public AccountType getRequestType() {
        return requestType;
    }

}
