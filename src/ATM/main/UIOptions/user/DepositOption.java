package main.UIOptions.user;

import main.clients.User;
import main.accounts.BankAccount;

/**
 * Deposit account option class
 */
public class DepositOption extends UserUIOption{


    public DepositOption(UserUI ui, User client) {
        super("DEPOSIT_TO_ACCOUNT", ui, client);
    }


    @Override
    public void select() {
        System.out.println("Select the account you would like to deposit to:");

        BankAccount depositingAccount = inputChecker.pickAccount(client);
        System.out.println("Enter how much you would like to deposit. Only bills can be deposited.");
        int depositAmount = inputChecker.inputCashable();

        (depositingAccount).deposit(depositAmount);
        client.addTransaction("Deposit", depositingAccount, depositAmount);

        depositingAccount.updateBills(depositAmount, "Deposit");

        System.out.println("Deposit successful. New balance for " + depositingAccount + " is: "
                + depositingAccount.getBalance());
    }
}
