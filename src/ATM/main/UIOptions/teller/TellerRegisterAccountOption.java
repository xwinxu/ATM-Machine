package main.UIOptions.teller;

import main.clients.BankTeller;
import main.clients.User;
import main.clients.UserRequest;
import main.accounts.AccountType;

/**
 * Opportunity for the teller to register an account to gain experience
 */
public class TellerRegisterAccountOption extends BankTellerUIOption {

    public TellerRegisterAccountOption(BankTellerUI ui, BankTeller client) {
        super("SETUP_ASSET_ACCOUNTS", ui, client);
    }

    @Override
    public void select() {
        for (UserRequest request : clientManager.getAccountRequestList()) {
            User user = request.getUser();
            AccountType requestType = request.getRequestType();
            String approval = inputChecker.prompt("Approve account creation for " + user.getUsername() +
                    "'s request for " + request.getRequestType() + " ACCOUNT? Y/N");
            if (requestType == AccountType.CHEQUING || requestType == AccountType.SAVINGS) {
                if (approval.equalsIgnoreCase("Y")) {
                    client.registerAccount(request);
                } else {
                    System.out.println("Denied");
                }
            } else {
                System.out.println("DENIED: Please see a bank manager for creation of credit/line-of-credit accounts.");
            }
        }
        clientManager.getAccountRequestList().clear();
        clientManager.saveMap(clientManager.getPath());
    }
}
