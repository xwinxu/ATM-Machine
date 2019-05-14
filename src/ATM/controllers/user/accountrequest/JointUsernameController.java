package controllers.user.accountrequest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.BankClient;
import main.clients.ClientManager;
import javafx.scene.control.TextField;
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JointUsernameController implements Initializable {

    //FXML Attributes.
    @FXML
    private Button confirmButton;
    @FXML
    private TextField username;
    @FXML
    private Label errorMessage;

    //Initialized Attribute.
    private ClientManager cm = ClientManager.getInstance();

    /**
     * Checks if input user is valid to request a joint account.
     */
    @FXML
    public void go() throws IOException {
        boolean validUser = (checkNotCurrentUser() && checkNumOfAccount() && checkUserExist()
                && checkOtherUserNumOfAccounts());

        if (validUser) {
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
            User user = new User("", "");
            for (BankClient client : cm.getUserList()) {
                if (client.getUsername().equals(username.getText())) {
                    user = (User) client;
                }
            }
            for (BankClient client : cm.getTellerList()){
                if (client.getUsername().equals(username.getText())){
                    user = (User) client;
                }
            }

            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accountRequest.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            AccountRequestController accountRequestController = loader.getController();
            accountRequestController.otherUser = user;
            accountRequestController.setIsJoint(true);
            stage.setTitle("Request New Account");
            stage.setResizable(false);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            if (!checkUserExist()) {
                errorMessage.setText("User does not exist.");
            } else if (!checkNotCurrentUser()) {
                errorMessage.setText("Joint user cannot be current user.");
            } else if (!checkNumOfAccount()) {
                errorMessage.setText("Current user has maximum accounts.");
            } else if (!checkOtherUserNumOfAccounts()) {
                errorMessage.setText("Joint user has maximum accounts.");
            }

        }
    }

    /**
     * Checks if username entered exists.
     *
     * @return if user exists.
     */
    private boolean checkUserExist() {
        boolean userExist = false;

        for (BankClient user : cm.getUserList()) {
            if (username.getText().equals(user.getUsername())) {
                userExist = true;
                return userExist;
            }
        }
        for (BankClient teller : cm.getTellerList()){
            if(username.getText().equals(teller.getUsername())){
                userExist = true;
            }
        }
        return userExist;
    }

    /**
     * Checks if username entered is not current user.
     *
     * @return if user is not current user.
     */
    private boolean checkNotCurrentUser() {
        boolean notCurrentUser = true;

        if (username.getText().equals(ClientManager.loggedInUser.getUsername())) {
            notCurrentUser = false;
        }
        return notCurrentUser;
    }

    /**
     * Checks if user has account spots.
     *
     * @return if user has account spots.
     */
    private boolean checkNumOfAccount() {
        boolean hasAccountSpots = false;
        int accounts = ClientManager.loggedInUser.getAccounts().size();

        if (accounts < 6) {
            hasAccountSpots = true;
        }
        return hasAccountSpots;
    }

    /**
     * Checks if Joint user has account spots.
     *
     * @return if  join user has account spots.
     */
    private boolean checkOtherUserNumOfAccounts() {
        boolean hasAccountSpace = false;
        User user = new User("", "");
        for (BankClient client : cm.getUserList()) {
            if (client.getUsername().equals(username.getText())) {
                user = (User) client;
            }
        }
        for (BankClient client : cm.getTellerList()){
            if (client.getUsername().equals(username.getText())){
                user = (User) client;
            }
        }
        int accounts = user.getAccounts().size();
        if (accounts < 6) {
            hasAccountSpace = true;
        }
        return hasAccountSpace;
    }

    /**
     * Overrides initialize.
     */
    @Override
    public void initialize (URL url, ResourceBundle rb){
        }
}
