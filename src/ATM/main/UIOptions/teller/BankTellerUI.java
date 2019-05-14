package main.UIOptions.teller;

import main.UIOptions.UI;
import main.clients.BankTeller;

/**
 * A bank teller UI class to containerize all its options
 */
public class BankTellerUI extends UI {

    public BankTellerUI(BankTeller teller){
        super(teller);
        options.put("1", new TellerRegisterAccountOption(this, teller));
        options.put("2", new PromotionRequestOption(this, teller));
        options.put("3", new TellerLogoutOption(this, teller));
    }
}
