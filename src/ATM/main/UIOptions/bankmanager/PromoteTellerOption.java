package main.UIOptions.bankmanager;

import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankClient;
import main.clients.BankManager;
import main.clients.BankTeller;

/**
 * The promote teller utilities
 */
public class PromoteTellerOption extends BankManagerUIOption {

    public PromoteTellerOption(BankManagerUI ui, BankManager manager) {
        super("PROMOTE_BANK_TELLER", ui, manager);
    }

    @Override
    public void select() {
        for (BankClient t : clientManager.getPendTellerList()) {
            String promote = inputChecker.prompt("Bank teller " + t.getUsername() + " has " + ((BankTeller) t).getExpPoints()
                    + " experience point(s), confirm to promote this teller to manager? Y/N");
            if (promote.equalsIgnoreCase("Y")) {
                client.promotePendingTeller((BankTeller) t);
            } else {
                System.out.println("Denied promotion.");
            }
        }
        clientManager.clearList("pendTeller");
    }
}
