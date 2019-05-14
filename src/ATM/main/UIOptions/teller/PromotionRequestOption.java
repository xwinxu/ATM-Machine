package main.UIOptions.teller;

import main.clients.BankTeller;

/**
 * To Request a Promotion Functionality
 */
public class PromotionRequestOption extends BankTellerUIOption {

    public PromotionRequestOption(BankTellerUI ui, BankTeller client) {
        super("REQUEST_PROMOTION", ui, client);
    }

    @Override
    public void select() {
        String confirm = inputChecker.prompt("Confirm to submit promotion request to bank manager? Y/N");
        if (confirm.equalsIgnoreCase("Y")){
            (client).promotionRequest();
        } else {
            System.out.println("\n");
        }
    }
}
