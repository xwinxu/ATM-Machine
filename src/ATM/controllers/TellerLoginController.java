package controllers;

import controllers.admin.AdminLoginController;
import controllers.teller.TellerEmployeeOptionsController;
import controllers.user.UserLoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import main.accounts.BankAccount;
import main.clients.ClientManager;
import main.clients.User;
import main.utilities.KeyValueFileHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TellerLoginController implements Initializable {

    //FXML Attributes.
    @FXML
    private Text usernameT;
    @FXML
    private Text dateT;
    public ArrayList<BankAccount> accounts;
    @FXML
    private Button logoutButton;
    @FXML
    private Button tellerUserButton;
    @FXML
    private Button tellerEmployeeButton;

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Sets up teller login options.
     */
    public void setUp(String username, String date, ArrayList<BankAccount> accounts){
        usernameT.setText("");
        dateT.setText("");
        usernameT.setText("User: " + username);
        dateT.setText("Date: "+ date);
        this.accounts = accounts;

    }
    /**
     * Logout Teller.
     */
    @FXML
    public void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Teller user options.
     */
    @FXML
    public void tellerUserOptions() throws IOException{
        Stage stage = (Stage) tellerUserButton.getScene().getWindow();
        stage.close();
        User user = ClientManager.loggedInTeller;
        ClientManager.loggedInUser = user;
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user/userlogin.fxml"));
        loader.load();
        UserLoginController userLoginController = loader.getController();
        KeyValueFileHandler handler = new KeyValueFileHandler();
        String date = handler.getFileValue("config.txt", "date");
        userLoginController.setUpText(user.getUsername(), date,user.getAccounts());
        Parent root = loader.getRoot();
        stage.setTitle("User Page");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    /**
     * Teller employee options.
     */
    @FXML
    public void tellerOptions() throws IOException {
        Stage stage = (Stage) tellerEmployeeButton.getScene().getWindow();
        stage.close();
        System.out.println("TellerOptions");

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tellerloginOptions.fxml"));
        loader.load();
        TellerEmployeeOptionsController tellerEmployeeOptionsController = loader.getController();
        tellerEmployeeOptionsController.setUp(usernameT.getText(),dateT.getText());
        Parent root = loader.getRoot();
        stage.setTitle("Teller Page");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
