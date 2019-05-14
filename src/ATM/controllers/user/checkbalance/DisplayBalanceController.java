package controllers.user.checkbalance;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayBalanceController implements Initializable {
    //Initialized Attributes.
    public BankAccount account;

    //FXML Attributes.
    @FXML
    private Button exitButton;
    @FXML
    private Label debtBalT;
    @FXML
    private Label accountBalLabel;

    /**
     * Sets the label in the scene with the account balance.
     */
    public void setBalanceText(){
        accountBalLabel.setText(account.getBalance());
    }

    /**
     * Return to previous scene.
     */
    public void returnToPrev(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Overrides initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
