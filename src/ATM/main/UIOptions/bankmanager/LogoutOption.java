package main.UIOptions.bankmanager;

import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankManager;

/**
 * Logout the user
 */
public class LogoutOption extends BankManagerUIOption {
    /**
     * Constructs a new BankMangerUI option
     *
     * @param ui   a BankManger user interface
     */
    public LogoutOption(BankManagerUI ui, BankManager manager) {
        super("LOGOUT", ui, manager);
    }

    /**
     * Asks manager to logout or not
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
