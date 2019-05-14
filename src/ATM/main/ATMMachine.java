package main;

import main.UIOptions.UI;
import main.UIOptions.bankmanager.BankManagerUI;
import main.UIOptions.teller.BankTellerUI;
import main.UIOptions.user.UserUI;
import main.clients.*;
import main.utilities.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * ATM Machine and corresponding behaviour.
 */
public class ATMMachine {

    //filepath with user list serialized
    private static final String CONFIG_FILENAME = "config.txt";
    private static final String CASH_INVENTOR_FILENAME = "cash_inventory.txt";
    private static final int DEFAULT_AMOUNT = 50;
    // file handler
    private KeyValueFileHandler handler;
    // check input object
    private InputChecker inputChecker;
    // client manager
    private ClientManager clientManager;

    private int attempt;

    /**
     * Constructor for ATM machine
     */
    public ATMMachine() {
        this.handler = new KeyValueFileHandler();
        this.inputChecker = new InputChecker();
        this.clientManager = ClientManager.getInstance();
        attempt = 0;
    }

    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();
        atm.initTime();
        atm.setupCash();
        while(true) {
            atm.run();
        }
    }

    public void initTime() {
        this.handler.setFileValue(CONFIG_FILENAME, "date", LocalDate.now());
    }

    /**
     * Display the current date
     */
    private void displayDate() {
        String date = handler.getFileValue(CONFIG_FILENAME, "date");
        System.out.println("Current date is: " + date);
    }

    /**
     * Automatically set up the cash inventory with default amount on initial startup
     */
    public void setupCash() {
        if (!new File(CASH_INVENTOR_FILENAME).exists()) {
            String[] cashes = new String[]{"5", "10", "20", "50"};
            for (String cash : cashes) {
                handler.setFileValue(CASH_INVENTOR_FILENAME, cash, DEFAULT_AMOUNT);
            }
            System.out.println("\n Cash inventory is now setup with 50 units of each currencies. \n");
        }
    }

    private void run() {
        displayDate();
        System.out.println(" < WELCOME TO THE ATM MACHINE > \n ----------------------------------");
        String userType = inputChecker.prompt("Please indicate if you are a new / returning user: " +
                "\n (1) Existing user \n (2) New user \n ----------------------------------\n");
        if (userType.equals("2")) {
            createUser();
            run();      // redirect back to the main login page
        } else if (userType.equals("1")) {
            login();
        } else {
            System.out.println("Invalid input, please try again...");
            run();
        }
    }

    /**
     * Create a new user for first time users
     */
    private void createUser() {
        String username = inputChecker.prompt("Please select a username \n");
        String userPass = inputChecker.prompt("Please select a password \n");
        if (inputChecker.validPassword(userPass)) {
            if (!clientManager.isExistingClient(username)) {
                clientManager.addToMasterList("pendUser", new User(username, userPass));
            } else {
                String again = inputChecker.prompt(">>> USERNAME IS TAKEN, TRY AGAIN? Y/N <<< \n");
                if (again.equalsIgnoreCase("Y")) {
                    createUser();
                } else if (again.equalsIgnoreCase("N")) {
                    System.out.println(">>> Redirecting you back to the main menu... ");
                    run();
                } else {
                    System.out.println(">> INVALID INPUT: REDIRECTING TO MAIN MENU ... <<<");
                    run();
                }
            }
        } else {
            System.out.println(">> PASSWORD TOO WEAK: MUST BE 6+ CHARACTERS WITH 1 UPPERCASE LETTER & 1 NUMBER <<<: ");
            createUser();
        }
        clientManager.saveMap(clientManager.getPath());
    }

    /**
     * Check client by type to login
     *
     * @param type     the type of BankClient
     * @param username Client's username
     * @param userPass Client's password
     * @return a BankClient registered at the bank
     */
    private BankClient loginClient(String type, String username, String userPass) {
        ArrayList<BankClient> clientList = clientManager.getClientList(type);
        while (attempt < 4) {
            for (BankClient client : clientList) {
                if (client.getUsername().equals(username) && client.getPassword().equals(userPass)) {
                    attempt = 0;
                    return client;
                }
            }
            System.out.println("Login unsuccessful, please try again (" + (3 - attempt) + "attempt(s) remaining): ");
            attempt++;
            login();
        }
        attempt = 0;
        System.out.println("\n Sorry, you will be redirected back to the main menu. \n");
        run();
        return null;
    }


    /**
     * Login any existing client at the bank
     */
    private void login() {
        System.out.println("Please selection an option: \n  ----------------------------------");
        String clientType = inputChecker.prompt(" 1: user \n 2: teller \n 3: manager \n  " +
                "---------------------------------- \n");
        if (!(clientType.equals("1") || clientType.equals("2") || clientType.equals("3"))) {
            System.out.println("Invalid option, please try again");
            login();
        } else {
            String username = inputChecker.prompt("Please enter a username");
            String userPass = inputChecker.prompt("Please enter a password");
            UI ui;
            if (clientType.equals("1")) {
                User user = (User) loginClient("user", username, userPass);
                ui = new UserUI(user);
                if (ui.isRunning()) {
                    ui.displayOptions();
                }
            } else if (clientType.equals("2")) {
                BankTeller teller = (BankTeller) loginClient("teller", username, userPass);
                ui = new BankTellerUI(teller);
                if (ui.isRunning()) {
                    ui.displayOptions();
                }
            } else {
                BankManager manager = (BankManager) loginClient("manager", username, userPass);
                ui = new BankManagerUI(manager);
                if (ui.isRunning()) {
                    ui.displayOptions();
                }
            }
        }
        run();
    }
}