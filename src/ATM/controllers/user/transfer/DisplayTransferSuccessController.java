package controllers.user.transfer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Display for when a transfer was successful
 */
public class DisplayTransferSuccessController implements Initializable {
    @FXML
    private Label accountBalLabel;
    @FXML
    private Button exitButton;
    public void setBalanceText(BankAccount account) {
        accountBalLabel.setText(account.getBalance());
    }


    /*
    Logic for the return button
     */
    public void returnToPrev() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
