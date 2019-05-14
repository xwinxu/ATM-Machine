package controllers.user.transfer;

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

/*
The scene for transferring an amount to a user
 */
public class TransferAmountController implements Initializable {
    // Where to transfer to
    public BankAccount destination;

    // Indicates if an account is transferable
    public boolean isTransfer;

    // THe account from which to transfer
    public BankAccount accountFrom;

    @FXML
    private Label mssg;
    @FXML
    private TextField transferamount;
    @FXML
    private Button exitButton;
    @FXML

    /*
    Logic to make the transfer
     */
    public void transfer() throws IOException{
            String numeric = "^(\\d+\\.)?\\d+$";
            String amount = transferamount.getText();
            if (!amount.matches(numeric)) {
                mssg.setText("Invalid value. Please enter a cashable amount.");
            }else {

                float transferAmount = Float.parseFloat(transferamount.getText());
                if (!accountFrom.canWithdraw(transferAmount)) {
                    mssg.setText("Withdrawing amount will cause account balance \nbeyond minimum.");
                }else {
                    if (isTransfer) {
                        accountFrom.transfer(destination, transferAmount);
                        System.out.println(destination + "" + accountFrom);
                        ClientManager.loggedInUser.addTransaction("Transfer", destination, accountFrom, transferAmount);

                    } else {
                        accountFrom.withdraw(transferAmount);
                        ClientManager.loggedInUser.addTransaction("Transfer - Bill Payment", accountFrom, transferAmount);
                    }
                    ClientManager clientManager = ClientManager.getInstance();
                    clientManager.saveMap(clientManager.getPath());
                    Stage stage = (Stage) exitButton.getScene().getWindow();
                    stage.close();

                    stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("displayTransferSuccess.fxml"));
                    loader.load();

                    DisplayTransferSuccessController displayTransferSuccessController = loader.getController();
                    displayTransferSuccessController.setBalanceText(accountFrom);

                    Parent root = loader.getRoot();
                    stage.setTitle("Transfer Success");
                    stage.setResizable(false);

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
