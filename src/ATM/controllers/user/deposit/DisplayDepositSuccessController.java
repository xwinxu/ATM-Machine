package controllers.user.deposit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayDepositSuccessController implements Initializable {
    // Initialized Attributes.
    public BankAccount account;

    // FXML Attributes.
    @FXML
    private Button exitButton;
    @FXML
    private Label debtBalT;
    @FXML
    private Label currBal;

    /**
     * Sets current balance after deposit to label on scene.
     */
    public void setBalanceText(BankAccount account) {
        currBal.setText(account.getBalance());
    }

    /**
     * Return to previous scene.
     */
    public void returnToPrev() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}