package main.UIOptions.user;

import main.clients.User;

/**
 * The current user logout options
 */
public class UserLogoutOption extends UserUIOption {


    public UserLogoutOption(UserUI ui, User client) {
        super("LOGOUT", ui, client);
    }

    /**
     * Asks user to logout or not
     */
    @Override
    public void select() {
        String end = inputChecker.prompt("Logout? Y/N");
        if (end.equalsIgnoreCase("Y")) {
            ui.setRunning(false);
            clientManager.saveMap(clientManager.getPath());
        }
    }
}
