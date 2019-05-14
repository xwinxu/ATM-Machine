package controllers.user.transfer;

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
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller to display the transfer accounts
 */
public class DisplayTransferAccountsController implements Initializable {
    // Indicator of whether an account is transferable
    public boolean isTransfer;

    // If you're transferring to yourself
    public boolean isSelf;

    // The master list of bank accounts currently in the bank
    public ArrayList<BankAccount> account = ClientManager.loggedInUser.getAccounts();

    // The receiver of the transfer
    public BankClient transferReceiver;

    // GUI buttons and elements
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Label mssg;

    // list of buttons on the GUI scene
    public ArrayList<Button> buttons = new ArrayList<>();

    /*
    To set up the account ids
     */
    public void setAccounts(){
        mssg.setText("");
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);

        for(int i = 0; i < account.size(); i ++){
            if (i < 6) {
                System.out.println(account.get(i).accountID);
                String[] accountID = account.get(i).accountID.split(" ");
                buttons.get(i).setText(accountID[accountID.length-1]);
            }
        }

    }

    @FXML
    public void account1() throws IOException {
        if (account.size() > 0) {
            displayAmount(account.get(0));
        }    }
    @FXML
    public void account2() throws IOException{
        if (account.size() > 1) {
            displayAmount(account.get(1));
        }
    }
    @FXML
    public void account3() throws IOException{
        if (account.size() > 2) {
            displayAmount(account.get(2));
        }
    }
    @FXML
    public void account4() throws IOException{
        if (account.size() > 3) {
            displayAmount(account.get(3));
        }
    }
    @FXML
    public void account5() throws IOException {
        if (account.size() > 4) {
            displayAmount(account.get(4));
        }
    }
    @FXML
    public void account6() throws IOException{
        if(account.size()>5) {
            displayAmount(account.get(5));
        }

    }

    /*
    Display the amount of balance in each account
     */
    public void displayAmount(BankAccount account) throws IOException{
        System.out.println(account.accountID);
        if(account.accountID.contains("Credit Card")){
            mssg.setText("Cannot transfer credit accounts");
        }else {
            mssg.setText("");
            Stage stage = (Stage) button1.getScene().getWindow();
            stage.close();
            if (isTransfer ) {
                if (isSelf){
                    stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("transferToAccount.fxml"));
                    loader.load();
                    TransferToAccountController transferToAccountController = loader.getController();
                    transferToAccountController.accountFrom = account;
                    User user = ClientManager.loggedInUser;
                    transferToAccountController.account = user.getAccounts();
                    transferToAccountController.isTransfer = isTransfer;
                    transferToAccountController.setAccounts();
                    Parent root = loader.getRoot();

                    stage.setTitle("Transfer Amount");
                    stage.setResizable(false);

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else {
                    stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("transferotheruser.fxml"));
                    loader.load();
                    TransferOtherUserController transferOtherUserController = loader.getController();
                    transferOtherUserController.account = ClientManager.loggedInUser.getAccounts();
                    transferOtherUserController.isTransfer = isTransfer;
                    transferOtherUserController.accountFrom = account;
                    Parent root = loader.getRoot();

                    stage.setTitle("Transfer Amount");
                    stage.setResizable(false);

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("transferamount.fxml"));
                loader.load();
                TransferAmountController transferAmountController = loader.getController();
                transferAmountController.isTransfer = isTransfer;
                transferAmountController.destination = account;
                transferAmountController.accountFrom = account;
                Parent root = loader.getRoot();

                stage.setTitle("Transfer Amount");
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
