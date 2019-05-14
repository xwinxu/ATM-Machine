package main.UIOptions.bankmanager;


import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankClient;
import main.clients.BankManager;
import main.clients.User;
import main.clients.UserRequest;

/**
 * A BankManagerUIOption for setting up new accounts
 */

public class SetUpAccountOption extends BankManagerUIOption {
    /**
     * Constructs a new set-up account option
     *
     * @param ui a BankManger user interface
     */
    public SetUpAccountOption(BankManagerUI ui, BankManager manager) {
        super("SETUP_ACCOUNT", ui, manager);
    }

    /**
     * Method for setting up a pending user's new account
     */
    @Override
    public void select() {
        for (UserRequest request : clientManager.getAccountRequestList()) {
            User user = request.getUser();
            String approval = inputChecker.prompt("Approve account creation for " + user.getUsername() +
                    "'s request for " + request.getRequestType() + " ACCOUNT? Y/N");
            if (approval.equalsIgnoreCase("Y")) {
//                System.out.println(user.getAccounts());
                client.registerAccount(request);
                System.out.println(user.getUsername() + "'s account(s): " + user.getAccounts());
            } else {
                System.out.println("Denied");
//                System.out.println(user.getAccounts());
            }
        }
        clientManager.getAccountRequestList().clear();
        clientManager.saveMap(clientManager.getPath());
    }
}
