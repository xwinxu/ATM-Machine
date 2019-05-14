package controllers.user.withdraw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayWithSuccessController implements Initializable {
    public BankAccount account;
    @FXML
    private Button exitButton;
    @FXML
    private Label debtBalT;
    @FXML
    private Label currBal;

    public void setBalanceText(BankAccount account) {
        this.account = account;
        currBal.setText(this.account.getBalance());
    }
    @FXML
    public void returnToPrev() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
