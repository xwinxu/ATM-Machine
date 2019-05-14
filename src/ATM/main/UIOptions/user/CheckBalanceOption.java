package main.UIOptions.user;

import main.clients.User;
import main.accounts.BankAccount;

/**
 * Class related to checking balance and relevant options
 */
public class CheckBalanceOption extends UserUIOption{

    public CheckBalanceOption(UserUI ui, User client) {
        super("CHECK_BALANCE", ui, client);
    }

    @Override
    public void select() {
        System.out.println("Select the account for which you would like to view the balance.");
        BankAccount chosen = inputChecker.pickAccount(client);
        System.out.print("The balance of " + chosen.accountID + " is: " + chosen.getBalance() + "\n");
    }


}
