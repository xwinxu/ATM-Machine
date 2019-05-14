package main.UIOptions.user;

import main.clients.User;

/**
 * Class to list options related to password resetting
 */
public class ChangePassOption extends UserUIOption {
    public ChangePassOption(UserUI ui, User client) {
        super("CHANGE_PASSWORD", ui, client);
    }

    @Override
    public void select() {
        String oldPassword = inputChecker.prompt("Please enter your current password:");
        if (oldPassword.equals(client.getPassword())) {
            setNewPassword(client);
        } else {
            System.out.println("Incorrect password. Please try again.");
            changePass(client);
        }

    }

    /**
     * To set a new password for current user
     *
     * @param user the current user
     */
    private void setNewPassword(User user) {
        String newPassword = inputChecker.prompt("Enter your new password:");
        String newPasswordSecond = inputChecker.prompt("Re-enter your new password:");
        if (newPassword.equals(newPasswordSecond)) {
            user.setPassword(newPassword);
        } else {
            System.out.println("Passwords don't match, please try again.");
            setNewPassword(user);
        }
    }

    /**
     * Method to change the password for the current user
     *
     * @param user the current user
     */
    private void changePass(User user) {
        String oldPassword = inputChecker.prompt("Please enter your current password:");
        if (oldPassword.equals(user.getPassword())) {
            setNewPassword(user);
        } else {
            System.out.println("Incorrect password. Please try again.");
            changePass(user);
        }
    }

}
