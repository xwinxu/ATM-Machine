package main.UIOptions.bankmanager;

import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankClient;
import main.clients.BankManager;
import main.clients.User;

/**
 * To undo a transaction for a given history
 */
public class UndoTransactionOption extends BankManagerUIOption {
    /**
     * Constructs a new BankMangerUI option
     *
     * @param ui a BankManger user interface
     */
    public UndoTransactionOption(BankManagerUI ui, BankManager manager) {
        super("UNDO_TRANSACTION", ui, manager);
    }

    /**
     * Undo the most recent transaction for given user
     */
    @Override
    public void select() {
        String userName = inputChecker.prompt("Please enter the user name to undo transaction for");
        for (BankClient u : clientManager.getUserList()) {
            User user = (User) u;
            if (userName.equalsIgnoreCase(user.getUsername())) {
                if (!client.undoLastTransaction(user)) {
                    System.out.println("Cannot undo transaction");


                } else {
                    System.out.println("Undo transaction successfully");
                }
                return;
            }
        }

        System.out.println("Cannot find user by name = " + userName);
    }
}
