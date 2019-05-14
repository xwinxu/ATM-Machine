package main.UIOptions.bankmanager;

import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.bankmanager.BankManagerUIOption;
import main.clients.BankClient;
import main.clients.BankManager;

/**
 * A BankManagerUIOption for restocking cash into ATM
 */
public class RestockMachineOption extends BankManagerUIOption {
    /**
     * Constructs a new restock machine option
     *
     * @param ui a BankManger user interface
     */
    public RestockMachineOption(BankManagerUI ui, BankManager manager) {
        super("RESTOCK", ui, manager);
    }

    /**
     * Restock cash / currencies in the ATM
     */
    @Override
    public void select() {
        String[] cashes = new String[]{"5", "10", "20", "50"};

        for (String cash : cashes) {
            int amount = client.getCurrencyAmount(cash);
            System.out.println("\nCurrent amount of $" + cash + ": " + amount + " units.\n");
            int newAmount = Integer.parseInt(inputChecker.prompt("Input the amount of $" + cash +"(s) you wish to put into this ATM"));
            client.restock(cash, newAmount);
        }
    }
}
