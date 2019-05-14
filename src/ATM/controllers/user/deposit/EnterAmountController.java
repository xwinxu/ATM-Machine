package controllers.user.deposit;

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

public class EnterAmountController implements Initializable {

    //Initialized Attribute.
    public BankAccount account;

    //FXML Attributes.
    @FXML
    private Label depositmssg;
    @FXML
    private TextField depositamount;
    @FXML
    private Button exitButton;
    @FXML

    /**
     * Deposits entered amount into corresponding user accounts.
     */
    public void deposit() throws IOException {
        String numeric = "^(\\d+\\.)?\\d+$";
        String amount = depositamount.getText();
        if (!amount.matches(numeric) || !(Math.round(Float.parseFloat(amount)) % 5 == 0)) {
            depositmssg.setText("Invalid value. Please enter a cashable amount.");
        }else {
            float depositingAmount = Float.parseFloat(depositamount.getText());
            System.out.println();
            account.deposit(depositingAmount);
            System.out.println();
            account.updateBills(Math.round(depositingAmount),"Deposit");
            ClientManager.loggedInUser.addTransaction("Deposit",account,depositingAmount);
            ClientManager clientManager = ClientManager.getInstance();
            clientManager.saveMap(clientManager.getPath());
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();

            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("displayDepositSuccess.fxml"));
            loader.load();
            DisplayDepositSuccessController displayDepositSuccessController = loader.getController();
            displayDepositSuccessController.setBalanceText(account);
            Parent root = loader.getRoot();
            stage.setTitle("Deposit Success");
            stage.setResizable(false);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
