package main.UIOptions.user;

import main.clients.User;
import main.accounts.AccountType;

/**
 * New Account functionality
 */
public class NewAccountOption extends UserUIOption {
    public NewAccountOption(UserUI ui, User client) {
        super("REQUEST_NEW_ACCOUNT", ui, client);
    }

    @Override
    public void select() {
        System.out.println("Please select what kind of account you would like to create:");
        String[] accountOptions = {"1 - Chequing Account ", "2 - Savings Account ", "3 - Credit Card Account ",
                "4 - Line Of Credit Account ", "5 - Overseas Account "};
        client.requestNewAccount(displayAccountOptions(accountOptions));
        clientManager.saveMap(clientManager.getPath());
    }

    /**
     * Display the ATM's functions and returns the function the user selected
     *
     * @param options a list of all the functionality options
     * @return the function the user has chosen
     */
    private AccountType displayAccountOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i] + "enter \"" + (i + 1) + "\".");
        }
        String input = inputChecker.prompt("");
        return getAccountOption(input);
    }

    /**
     * Return the options upon login screen in string format
     *
     * @param optionChosen the option given to display
     * @return whether there are options available
     */
    private AccountType getAccountOption(String optionChosen) {
        switch (optionChosen) {
            case "1":
                return AccountType.CHEQUING;
            case "2":
                return AccountType.SAVINGS;
            case "3":
                return AccountType.CREDIT;
            case "4":
                return AccountType.LINE_CREDIT;
            case "5":
                return AccountType.OVERSEAS;
            default:
                String input = inputChecker.prompt("Please enter a valid option.");
                getAccountOption(input);
        }
        return null;
    }
}
