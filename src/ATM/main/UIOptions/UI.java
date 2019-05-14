package main.UIOptions;

import main.clients.BankClient;
import main.utilities.InputChecker;

import java.util.Map;
import java.util.TreeMap;

public class UI {
    /**
     * A client at the bank
     */
    public BankClient client;
    /**
     * Options map containing all UI options
     */
    public Map<String, UIOption> options;
    /**
     * Flag for is the client's logged-in/logged-out status
     */
    public boolean running;
    /**
     * Utility class for handling inputs
     */
    public InputChecker inputChecker;

    /**
     * Construct a new instance of a user interface
     *
     * @param client the client using the interface
     */
    public UI(BankClient client) {
        this.client = client;
        this.running = true;
        this.inputChecker = new InputChecker();
        this.options = new TreeMap<>();
    }

    /**
     * Check to see if client has logged out
     *
     * @return boolean that is false if logged out
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Setter to change if the client program is still active
     *
     * @param active boolean - true if it is still active false if terminated
     */
    public void setRunning(boolean active) {
        this.running = active;
    }

    /**
     * Prompt client to select option and perform selected option
     */
    public void displayOptions() {
        // Get each map entry as a set
//        for (Map.Entry<String, T> e : options.entrySet()) {
        for (Map.Entry<String, UIOption> e : options.entrySet()) {
            // Print out: option number: name of that option
            System.out.println(e.getKey() + ": " + e.getValue().getName());
        }
        // Prompt to choose an option and store the input
        String selected = inputChecker.prompt("Please select an option");
        // Get selected option
        UIOption option = options.get(selected);
        if (option != null) {
            // perform option
            option.select();
            if (!running) {
                return;
            }
        } else {
            System.out.println("\nINVALID OPTION\n");
        }
        displayOptions();
    }
}
