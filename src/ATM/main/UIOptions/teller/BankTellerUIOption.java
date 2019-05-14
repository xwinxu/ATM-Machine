package main.UIOptions.teller;

import main.UIOptions.UIOption;
import main.clients.BankTeller;

/**
 * Bank teller option on the teller menu
 */
public abstract class BankTellerUIOption extends UIOption<BankTellerUI, BankTeller> {

    public BankTellerUIOption(String name, BankTellerUI ui, BankTeller teller) {
        super(name, ui, teller);
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
