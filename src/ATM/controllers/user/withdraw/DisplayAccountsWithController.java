package controllers.user.withdraw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DisplayAccountsWithControler class for dispplaywithdrawaccounts.fxml
 */
public class DisplayAccountsWithController implements Initializable {
    //initializing list of bank accounts
    public ArrayList<BankAccount> account = ClientManager.loggedInUser.getAccounts();
    //items in the fxml
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
    //list of buttons in the fxml
    public ArrayList<Button> buttons = new ArrayList<>();

    //setup the buttons before drawing the new scene
    public void setAccounts(){
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
                buttons.get(i).setText(accountID[accountID.length-1]);            }
        }
    }

    /**
     * buttonlistener for button1
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account1() throws IOException {
        if (account.size() > 0) {
            displayAmount(account.get(0));
        }
    }
    /**
     * buttonlistener for button2
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account2() throws IOException{
        if (account.size() > 1) {
            displayAmount(account.get(1));
        }
    }
    /**
     * buttonlistener for button3
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account3() throws IOException{
        if (account.size() > 2) {
            displayAmount(account.get(2));
        }
    }
    /**
     * buttonlistener for button4
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account4() throws IOException{
        if (account.size() > 3) {
            displayAmount(account.get(3));
        }
    }
    /**
     * buttonlistener for button5
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account5() throws IOException {
        if (account.size() > 4) {
            displayAmount(account.get(4));
        }
    }
    /**
     * buttonlistener for button6
     * @throws IOException for loading scene in displayAmount()
     */
    @FXML
    public void account6() throws IOException{
        if(account.size()>5) {
            displayAmount(account.get(5));
        }

    }

    /**
     * opens the withdraw amount scene when user presses one of the button with the account
     * @param account for the list of accounts to withdraw from
     * @throws IOException when attempting to load new scene
     */
    private void displayAmount(BankAccount account) throws IOException{
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("enterWithdrawAmount.fxml"));
        loader.load();
        EnterWithdrawController enterAmountController = loader.getController();
        enterAmountController.account = account;
        Parent root = loader.getRoot();

        stage.setTitle("Withdraw Amount");
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
