package main.UIOptions.teller;

import main.clients.BankTeller;

/**
 * Options for a teller
 */
public class TellerLogoutOption extends BankTellerUIOption {

    public TellerLogoutOption(BankTellerUI ui, BankTeller teller) {
        super("LOGOUT", ui, teller);
    }

    @Override
    public void select() {
        String end = inputChecker.prompt("Logout? Y/N");
        if (end.equalsIgnoreCase("Y")) {
            ui.setRunning(false);
            clientManager.saveMap(clientManager.getPath());
        }
    }
}
