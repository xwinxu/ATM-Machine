package main.UIOptions.bankmanager;

import main.UIOptions.*;
import main.clients.BankManager;


/**
 * A BankManger user interface
 */
public class BankManagerUI extends UI {

    /**
     * Constructs a BankManger User Interface
     *
     * @param manager a bank manager
     */
    public BankManagerUI(BankManager manager) {
        super(manager);
        // Add in all the options
        options.put("1", new RestockMachineOption(this, manager));
        options.put("2", new SetUpAccountOption(this, manager));
        options.put("3", new SetSystemDateOption(this, manager));
        options.put("4", new UndoTransactionOption(this, manager));
        options.put("5", new PromoteTellerOption(this, manager));
        options.put("6", new RegisterNewUserOption(this, manager));
        options.put("7", new LogoutOption(this, manager));
    }
}
