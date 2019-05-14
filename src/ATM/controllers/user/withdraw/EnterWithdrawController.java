package controllers.user.withdraw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * EnterWithdrawController class for the corresponding fxml files
 */
public class EnterWithdrawController implements Initializable {
    //field account
    public BankAccount account;
    // items in the fxml
    @FXML
    private Label mssg;
    @FXML
    private TextField withdrawamount;
    @FXML
    private Button exitButton;

    /**
     * performing withdraw, checks if user input is valid and withdraws that amount from the account chosen in the
     * previous scene
     * @throws IOException when loading new scene
     */
    @FXML
    public void withdraw() throws IOException{
        String numeric = "^(\\d+\\.)?\\d+$";
        String amount = withdrawamount.getText();
        if (!amount.matches(numeric) || Float.parseFloat(amount)%5 != 0) {
            mssg.setText("Invalid numerical value");
        }else {
            float withdrawAmount = Float.parseFloat(amount);
            if (!account.canWithdraw(withdrawAmount)) {
                mssg.setText("Withdrawing amount will cause account balance beyond minimum.");
            } else {
                account.withdraw(withdrawAmount);
                account.updateBills(Math.round(withdrawAmount),"Withdraw");
                ClientManager.loggedInUser.addTransaction("Withdraw",account,withdrawAmount);
                ClientManager clientManager = ClientManager.getInstance();
                clientManager.saveMap(clientManager.getPath());
                //opens the new scene displaying withdraw success
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("displayWithdrawSuccess.fxml"));
                loader.load();
                DisplayWithSuccessController displayWithSuccessController = loader.getController();
                displayWithSuccessController.setBalanceText(this.account);
                Parent root = loader.getRoot();
                stage.setTitle("Withdraw Success");
                stage.setResizable(false);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    //override the abstract method from the Initializable interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
