package controllers.user.transfer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.BankClient;
import main.clients.ClientManager;
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*
Controller for making a transfer to another user
 */
public class TransferOtherUserController implements Initializable {
    public BankAccount destination;
    public ArrayList<BankAccount> account;
    public boolean isTransfer;
    public BankAccount accountFrom;
    public BankClient clientReceivingTransaction;
    @FXML
    private Label mssg;
    @FXML
    private TextField getOtherUser;

    /**
     * Checks if username entered exists.
     *
     * @return if user is not current user.
     */
    public boolean checkUserExists() {
        boolean userExists = false;
        ClientManager clientManager = ClientManager.getInstance();
        ArrayList<BankClient> list = clientManager.getUserList();
        list.addAll(clientManager.getTellerList());
        for (BankClient client : list) {
            if (client.getUsername().equals(getOtherUser.getText())) {
                userExists = true;
                clientReceivingTransaction = client;
            }
        }
        return userExists;
    }

    /*
    Logic to transfer a sum to another account
     */
    @FXML
    public void go() throws IOException {
        boolean notUser = checkNotCurrentUser();
        boolean userExists = checkUserExists();
        if(userExists && notUser) {
            Stage stage = (Stage) getOtherUser.getScene().getWindow();
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("transferToAccount.fxml"));
            loader.load();
            TransferToAccountController transferToAccountController = loader.getController();
            transferToAccountController.accountFrom = accountFrom;
            User user = (User)clientReceivingTransaction;
            transferToAccountController.account = user.getAccounts();
            transferToAccountController.isTransfer = isTransfer;
            transferToAccountController.setAccounts();
            Parent root = loader.getRoot();

            stage.setTitle("Transfer Amount");
            stage.setResizable(false);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            if (!userExists) {
                mssg.setText("User does not exist. Please input a valid user");
            } else {
                mssg.setText("Cannot select current user. Please input a valid user");
            }
        }
    }

    /**
     * Checks if username entered is not current user.
     *
     * @return if user is not current user.
     */
    private boolean checkNotCurrentUser() {
        boolean notCurrentUser = true;

        if(getOtherUser.getText().equals(ClientManager.loggedInUser.getUsername())){
            notCurrentUser = false;
        }
        return notCurrentUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
