package main.UIOptions.bankmanager;

import main.ATMMachine;
import main.accounts.BankAccount;
import main.accounts.CreditCard;
import main.accounts.SavingsAccount;
import main.clients.BankClient;
import main.clients.BankManager;
import main.clients.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A BankManagerUIOption for setting system date
 */
public class SetSystemDateOption extends BankManagerUIOption {
    /**
     * Construct a new set-system-date option
     *
     * @param ui a BankManger user interface
     */
    public SetSystemDateOption(BankManagerUI ui, BankManager manager) {
        super("SET_DATE", ui, manager);
    }

    /**
     * Sets system date to inputted date
     */
    @Override
    public void select() {
        String date = inputChecker.prompt("Input desired date (YYYY-MM-DD)");
        LocalDate localDate = LocalDate.parse(date);
        applyInterest(localDate);
        client.setDate(localDate);
    }

    /**
     * applying interest to each user's account every first day of each month
     */
    public void applyInterest(LocalDate localDate) {
        System.out.println("apply interest ");
        if (localDate.getDayOfMonth() == 1) {
            for (BankClient u : clientManager.getUserList()) {
                User user = (User) u;
                ArrayList<BankAccount> accountList = user.getAccounts();
                for (BankAccount account : accountList) {
                    System.out.println(account.getClass());
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount)account).accumulateInterest();
                        System.out.println("SAVINGS GOT INTEREST");
                    } else if (account instanceof CreditCard){
                        ((CreditCard)account).accumulateInterest();
                        System.out.println("CREDIT CARD GAINED INTEREST :(");
                    }
                }
            }
        }
    }
}
