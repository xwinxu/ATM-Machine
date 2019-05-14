package main.UIOptions;

import main.clients.BankClient;
import main.clients.BankTeller;
import main.clients.ClientManager;
import main.utilities.InputChecker;

/**
 * An abstract UI options class that is parent to all ui options and can be used by clients
 *
 * @param <U> UiOption
 * @param <T> BankClient
 */
public abstract class UIOption<U extends UI, T extends BankClient> {
    /**
     * The name of the option
     */
    protected String name;
    /**
     * A BankClient at the bank
     */
    public T client;
    /**
     * A user interface
     */
    protected U ui;

    /**
     * Utility class for handling inputs
     */
    public InputChecker inputChecker;
    /**
     * A singleton class containing all client information
     */
    protected ClientManager clientManager;


    /**
     * Construct a new instance of a ui option
     *
     * @param name   the name of the function
     * @param ui     the user interface
     * @param client the client using the ui
     */
    public UIOption(String name, U ui, T client) {
        this.name = name;
        this.client = client;
        this.ui = ui;
        inputChecker = new InputChecker();
        clientManager = ClientManager.getInstance();
    }

    /**
     * Abstract class for carrying out selected option
     */
    public abstract void select();

    /**
     * Getter for the name of the option
     *
     * @return the name of the option
     */
    public String getName() {
        return name;
    }

}
