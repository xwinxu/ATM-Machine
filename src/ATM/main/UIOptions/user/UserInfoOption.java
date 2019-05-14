package main.UIOptions.user;

import main.accounts.*;
import main.clients.User;
import main.utilities.Transaction;

import java.util.List;

/**
 * For displaying user options
 */
public class UserInfoOption extends UserUIOption {

    /**
     * Options for user information
     */
    private String[] options = {"For account summary, ", "For recent transactions ",
            "For account creation date ", "For net total ", "To return "};

    public UserInfoOption(UserUI ui, User client) {
        super("USER_INFORMATION", ui, client);
    }

    @Override
    public void select() {
        for (int i = 0; i < options.length; i++) {
            if (i != options.length - 1) {
                System.out.println(options[i] + "enter \"" + (i + 1) + "\".");
            } else {
                System.out.println(options[i] + "enter \"BACK\".");
            }
        }
        String input = inputChecker.prompt("");
        getUserInfoOption(input);
    }

    /**
     * Return the options for User Information screen in string format
     *
     * @param optionChosen the option given to display
     * @return whether there are options available
     */
    private boolean getUserInfoOption(String optionChosen) {
        boolean inputFlag = false;
        while (!inputFlag) {

            switch (optionChosen) {
                case "1":
                    inputFlag = true;
                    accountSummary(client);
                    break;
                case "2":
                    inputFlag = true;
                    mostRecentTransactions(client);
                    break;
                case "3":
                    inputFlag = true;
                    showCreationDate(client);
                    break;
                case "4":
                    inputFlag = true;
                    obtainNetTotal(client);
                    break;
                case "BACK":
                    inputFlag = true;
                    UserUI ui = new UserUI(client);
                    ui.displayOptions();
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    String input = inputChecker.prompt("");
                    inputFlag = getUserInfoOption(input);
            }
        }
        return inputFlag;
    }

    /**
     * Display the summary of all accounts of user.
     */
    public void accountSummary(User user) {
        List<BankAccount> accounts = user.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof ChequingAccount) {
                System.out.println("Chequing Account");
                System.out.println("----------------------------------");
            } else if (accounts.get(i) instanceof SavingsAccount) {
                System.out.println("Savings Account");
                System.out.println("----------------------------------");
            } else if (accounts.get(i) instanceof LineOfCredit) {
                System.out.println("Line Of Credit Account");
                System.out.println("----------------------------------");
            } else if (accounts.get(i) instanceof CreditCard) {
                System.out.println("Credit Card Account");
                System.out.println("----------------------------------");
            } else if (accounts.get(i) instanceof OverseasAccount) {
                System.out.println("OverSeas Account");
                System.out.println("----------------------------------");
            }
            System.out.println("Balance: " + accounts.get(i).getBalance() + "\n");
        }
    }

    /**
     * Display the past five transactions.
     */
    public void mostRecentTransactions(User user) {
        List<Transaction> allTransactions = user.getTransactions();
        System.out.println(allTransactions);
        int len = allTransactions.size();
        if (len == 0){
            return;
        }
        System.out.println("Past Transactions: ");
        for (int i = 0; i < Math.min(len, 5); i++){
            System.out.println(allTransactions.get(len - 1 - i));
        }
    }

    /**
     * The current date of creation
     *
     * @param user the current user
     */
    private void showCreationDate(User user) {
        System.out.println("Select the account for which you would like the creation date:");
        BankAccount account = inputChecker.pickAccount(user);
        System.out.println(account.getCreationDate());
    }

    /**
     * Display the net total for all accounts of user.
     */
    public void obtainNetTotal(User user) {
        long debtAmount = 0;
        long assetAmount = 0;
        List<BankAccount> accounts = user.getAccounts();
        for (BankAccount account : accounts) {
            if (account.getIsDebt()) {
                assetAmount += account.getBalanceDouble();
            } else {
                debtAmount += account.getBalanceDouble();
            }
        }
        long total = assetAmount - debtAmount;
        System.out.println("Net Total: " + total);
    }
}


