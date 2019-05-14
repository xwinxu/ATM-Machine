package main.UIOptions.bankmanager;

import main.UIOptions.UIOption;
import main.clients.BankManager;

/**
 * A abstract class for all BankManger user interface options
 */
public abstract class BankManagerUIOption extends UIOption<BankManagerUI, BankManager> {
//    public BankManager manager;
    /**
     * Constructs a new BankMangerUI option
     *
     * @param name the name of the option
     * @param ui   a BankManger user interface
     */
    public BankManagerUIOption(String name, BankManagerUI ui, BankManager client) {
        super(name, ui, client);
    }
}
