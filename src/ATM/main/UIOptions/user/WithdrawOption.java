package main.UIOptions.user;

import main.clients.User;
import main.accounts.BankAccount;

/**
 * The withdraw option for user action Withdraw
 */
public class WithdrawOption extends UserUIOption {

    public WithdrawOption(UserUI ui, User client) {
        super("WITHDRAW", ui, client);
    }

    @Override
    public void select() {
        System.out.println("Please enter the amount you would like to withdraw:");

        int amount = inputChecker.inputCashable();

        System.out.println("Please select the account you would like to withdraw from");
        BankAccount withdrawFrom = inputChecker.pickAccount(client);
        if (withdrawFrom.canWithdraw(amount)) {
            withdrawFrom.withdraw(amount);
            (client).addTransaction("Withdraw", withdrawFrom, amount);
            withdrawFrom.updateBills(amount, "Withdraw");
            System.out.println("Withdrawal successful. New balance for " + withdrawFrom + " is: "
                    + withdrawFrom.getBalance());
        } else {
            System.out.println("Withdraw unsuccessful");
        }
    }
}
