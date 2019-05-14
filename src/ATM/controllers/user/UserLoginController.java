package controllers.user;

import controllers.user.checkbalance.DisplayBalanceAccountsController;
import controllers.user.deposit.DisplayAccountsController;
import controllers.user.withdraw.DisplayAccountsWithController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserLoginController implements Initializable {
    public static String task;

    //FXML Attributes.
    @FXML
    private Text usernameT;
    @FXML
    private Text dateT;
    @FXML
    private Text accountsT;
    @FXML
    private Button logoutButton;

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Sets up login user information text labels.
     */
    public void setUpText(String username, String date, ArrayList<BankAccount> accounts){
        usernameT.setText("");
        dateT.setText("");
        accountsT.setText("");
        usernameT.setText("User: " + username);
        dateT.setText("Date: "+ date);
        if(accounts.size() == 0){
            accountsT.setText("No Account Found");
        }
        for (BankAccount account : accounts){
            if (accounts.indexOf(account) < 6) {
                accountsT.setText(accountsT.getText() + account + "\n");
            }
        }
    }

    /**
     * Opens check balance scenes and functions.
     */
    @FXML
    public void checkBalance() throws IOException {
        System.out.println("checkBalance");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("checkbalance/displayBalAccounts.fxml"));
        loader.load();
        DisplayBalanceAccountsController displayBCon = loader.getController();
        displayBCon.setAccounts();
        Parent root = loader.getRoot();
        stage.setTitle("Check Balance");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens deposit scenes and functions.
     */
    @FXML
    public void depositMoney() throws IOException{
        task = "deposit";
        System.out.println("deposit");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("deposit/displayAccounts.fxml"));
        loader.load();
        DisplayAccountsController displayACon = loader.getController();

        displayACon.setAccounts();

        Parent root = loader.getRoot();

        stage.setTitle("Deposit");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens transfer scenes and functions.
     */
    @FXML
    public void transfer() throws IOException{
        System.out.println("transfer");
        task = "transfer";
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transfer/transferoption.fxml"));
        loader.load();

        Parent root = loader.getRoot();

        stage.setTitle("Transfer");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens account information scenes and functions.
     */
    @FXML
    public void accountInfo() throws IOException {
        task = "accountInfo";
        System.out.println("userinfo");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userinfo/displayUserInfoOptions.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens withdraw scenes and functions.
     */
    @FXML
    public void withdraw() throws IOException{
        task = "withdraw";
        System.out.println("withdraw");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("withdraw/displayWithdrawAccounts.fxml"));
        loader.load();
        DisplayAccountsWithController displayAccountsWithController = loader.getController();
        displayAccountsWithController.setAccounts();
        Parent root = loader.getRoot();

        stage.setTitle("Withdraw");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Opens change password scenes and functions.
     */
    @FXML
    public void changePass() throws IOException{
        task = "changePass";
        System.out.println("changePass");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("changepass/changePass.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Change Password");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Logout user.
     */
    @FXML
    public void logout(){
        task = null;
        ClientManager clientManager = ClientManager.getInstance();
        clientManager.saveMap(clientManager.getPath());
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens request new account scenes and functions.
     */
    @FXML
    public void requestNewAccount() throws IOException{
        task = "request";
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("accountrequest/jointSelection.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Request New Account");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    }
}
