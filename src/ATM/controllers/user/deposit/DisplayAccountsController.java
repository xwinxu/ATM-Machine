package controllers.user.deposit;

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

public class DisplayAccountsController implements Initializable {

    //FXML Attributes.
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

    //Initialized Attributes
    public ArrayList<Button> buttons = new ArrayList<>();
    public ArrayList<BankAccount> account = ClientManager.loggedInUser.getAccounts();

    /**
     * Sets buttons on scene to corresponding user accounts.
     */
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
                buttons.get(i).setText(accountID[accountID.length-1]);
            }
        }

    }

    /**
     * Displays amount to be deposit to corresponding accounts.
     */
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

    /**
     * Displays amount to be deposited to account.
     */
    public void displayAmount(BankAccount account) throws IOException{

        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("enterAmount.fxml"));
        loader.load();
        EnterAmountController enterACon = loader.getController();
        enterACon.account = account;
        Parent root = loader.getRoot();

        stage.setTitle("Deposit Amount");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
