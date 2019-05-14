package controllers.user.userinfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.*;
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AccountSummaryController class for the fxml that displays the account summary for each account
 */
public class AccountSummaryController implements Initializable {
    //items in the fxml
    @FXML
    private Button exitButton;
    @FXML
    private Label scrollAct;

    /**
     * sets the account summary with a text displaying the account summary of each account
     * @param user the user that has the list of accounts
     */
    @FXML
    public void setAccountSummary(User user) {
        StringBuilder ret = new StringBuilder();
        List<BankAccount> accounts = user.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof ChequingAccount) {
                ret.append("Chequing Account ");
            } else if (accounts.get(i) instanceof SavingsAccount) {
                ret.append("Savings Account ");
            } else if (accounts.get(i) instanceof LineOfCredit) {
                ret.append("Line Of Credit Account ");
            } else if (accounts.get(i) instanceof CreditCard) {
                ret.append("Credit Card Account ");
            } else if (accounts.get(i) instanceof OverseasAccount) {
                ret.append("OverSeas Account ");
            }
            ret.append("Balance: ");
            ret.append( accounts.get(i).getBalance());
            ret.append("\n");
        }
        scrollAct.setText(ret.toString());
    }

    /**
     * returns to the previous scene of displayUserInfoOptions.fxml and closes the current scene
     * @throws IOException when loading new scene
     */
    @FXML
    public void returnButton() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayUserInfoOptions.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //override the abstract method from the Initializable interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
