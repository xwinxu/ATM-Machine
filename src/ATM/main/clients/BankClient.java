package main.clients;

import java.io.Serializable;

/**
 * The Bank Client with username and password
 */
public class BankClient implements Serializable {
    /**
     * Client's username
     */
    private String username;
    /**
     * Client's password
     */
    private String password;

    /**
     * Construct a new instance of a bank client
     *
     * @param username the username of the client
     * @param password the pass of the client
     */
    public BankClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Setter for username
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for username
     *
     * @return the current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for password
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for password
     *
     * @return the current password
     */
    public String getPassword() {
        return password;
    }

    /**
     * For printing purposes
     *
     * @return the string representation
     */
    public String toString() {
        return (username + " " + password);
    }
}
