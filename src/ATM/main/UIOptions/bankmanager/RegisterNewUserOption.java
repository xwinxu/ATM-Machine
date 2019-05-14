package main.UIOptions.bankmanager;

import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankClient;
import main.clients.BankManager;
import main.clients.User;

/**
 * Register new user functionality
 */
public class RegisterNewUserOption extends BankManagerUIOption {
    /**
     * Constructs a new BankMangerUI option
     *
     * @param ui      a BankManger user interface
     * @param manager   a bank manager at the bank
     */
    public RegisterNewUserOption(BankManagerUI ui, BankManager manager) {
        super("REGISTER_NEW_USER", ui, manager);
    }

    @Override
    public void select() {
        for (BankClient user : clientManager.getPendUserList()) {
            String approval = inputChecker.prompt("Approve " + user.getUsername() + "'s creation request? Y/N");
            if (approval.equalsIgnoreCase("Y")) {
                client.registerUser((User) user);
            } else {
                System.out.println("Denied creation request.");
            }
        }
        clientManager.clearList("pendUser");
    }
}
