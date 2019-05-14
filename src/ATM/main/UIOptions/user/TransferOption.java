package main.UIOptions.user;

import main.clients.BankClient;
import main.accounts.CreditCard;
import main.clients.User;
import main.accounts.BankAccount;
import main.utilities.KeyValueFileHandler;

import java.time.LocalDate;

/**
 * A transfer functionality and according types
 */
public class TransferOption extends UserUIOption {

    private KeyValueFileHandler handler;

    public TransferOption(UserUI ui, User client) {
        super("TRANSFER", ui, client);
        handler = new KeyValueFileHandler();
    }

    @Override
    public void select() {
        System.out.println("To transfer within your account, enter 1. \nTo transfer to another user, enter 2. \n" +
                "To pay an external bill, enter 3");
        int input = inputChecker.inputInt();

        boolean inputFlag = false;

        while (!inputFlag) {
            switch (input) {
                case 1:
                    internalTransfer(client);
                    inputFlag = true;
                    break;
                case 2:
                    externalTransfer(client);
                    inputFlag = true;
                    break;
                case 3:
                    payBill(client);
                    inputFlag = true;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    input = inputChecker.inputInt();
            }
        }
    }


    /**
     * To make a transfer to an external payee
     *
     * @param user the current user
     */
    private void payBill(User user) {
        System.out.println("Select the account you would like to transfer money from:");
        BankAccount accountFrom = inputChecker.pickAccount(user);
        String payee = inputChecker.prompt("Enter the payee to which you would like to pay your bill:");

        System.out.println("Enter the amount you would like to transfer: ");
        float amount = inputChecker.inputFloat();

        if (accountFrom.canWithdraw(amount) && !(accountFrom instanceof CreditCard)) {
            accountFrom.withdraw(amount);
            user.addTransaction("Transfer - Bill Payment", accountFrom, amount);
            handler.setFileValue("outgoing.txt", payee, " Paid: " + amount + " on " + LocalDate.now());
        } else if (accountFrom instanceof CreditCard) {
            System.out.println("Transfers cannot be made from credit cards.");
        } else {
            System.out.println("Transfer could not be completed due to insufficient funds.");
        }
    }


    /**
     * To make a transfer to a client
     *
     * @param user the current user
     */
    private void externalTransfer(User user) {
        String other = inputChecker.prompt("Enter the username of the client you would like to transfer to:");
        for (BankClient c : clientManager.getUserList()) {
            User client = (User) c;
            if (client.getUsername().equalsIgnoreCase(other)) {
                System.out.println("Select the account you would like to transfer money from:");
                BankAccount accountFrom = inputChecker.pickAccount(user);
                System.out.println("Select the account you would like to transfer money to:");
                BankAccount accountTo = inputChecker.pickAccount(client);

                System.out.println("Enter the amount you would like to transfer.");
                float amount = inputChecker.inputFloat();

                int result = accountFrom.transfer(accountTo, amount);

                if (result == -1) {
                    System.out.println("Transfer not completed.");
                    return;
                } else {
                    System.out.println("Transfer successful.");
                    user.addTransaction("Transfer", accountTo, accountFrom, amount);
                    return;
                }
            }
        }
        System.out.println("This user could not be found.");
    }

    /**
     * To transfer to an account internally.
     *
     * @param user the current user
     */
    private void internalTransfer(User user) {
        System.out.println("Select the account you would like to transfer money from:");
        BankAccount accountFrom = inputChecker.pickAccount(user);
        System.out.println("Select the account you would like to transfer money to:");
        BankAccount accountTo = inputChecker.pickAccount(user);

        while (accountFrom == accountTo) {
            System.out.println("Invalid accounts. Cannot transfer to the same account. \nSelect the account " +
                    "you would like to transfer money to:");
            accountTo = inputChecker.pickAccount(user);
        }

        System.out.println("Enter the amount you would like to transfer: ");
        float amount = inputChecker.inputFloat();

        int result = accountFrom.transfer(accountTo, amount);

        if (result == -1) {
            System.out.println("Transfer not completed.");
        } else {
            System.out.println("Transfer successful.");
            user.addTransaction("Transfer", accountTo, accountFrom, amount);
        }
    }
}