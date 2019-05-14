package controllers.admin;

import controllers.admin.restock.AddFiveController;
import controllers.admin.setupAcc.DisplayReqAccountsController;
import controllers.admin.setupTeller.DisplayReqTellerController;
import controllers.admin.setupUser.DisplayReqUserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.clients.ClientManager;
import main.utilities.KeyValueFileHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AdminLoginController class for drawing a scene for manager's main option hub page
 */
public class AdminLoginController implements Initializable {
    //initialize clientManager
    private ClientManager clientManager = ClientManager.getInstance();
    //items in adminlogin.fxml
    @FXML
    private Button logoutButton;

    @FXML
    private Text currentDate;
    @FXML
    private Text usernameT;
    //keyValueHandler for getting date
    private KeyValueFileHandler handler;

    //initialize abstracyt method from interface Initializable
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * sets the fields before drawing the scene
     * @param username
     * @param date
     */
    public void setUpText(String username, String date){
        usernameT.setText("");
        currentDate.setText("");
        usernameT.setText("Admin: " + username);
        currentDate.setText("Date: "+ date);
    }

    /**
     * opens the setup for cash inventory
     * @throws IOException
     */
    @FXML
    public void setupCI() throws IOException{
        System.out.println("setupCI");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayConfirmationCashInventory.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Cash Inventory");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * set up new accounts requested by user
     * @throws IOException throws IOException
     */
    @FXML
    public void setupAcc()throws IOException{
        System.out.println("setupACC");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setupAcc/displayReqAccounts.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        DisplayReqAccountsController displayReqAccountsController = loader.getController();
        displayReqAccountsController.getAccounts();
        stage.setTitle("Requested Accounts");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * undo transaction for a specific user
     * @throws IOException ioexception for loading new scene
     */
    @FXML
    public void undoTransaction() throws IOException{
        System.out.println("undoTransaction");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("undoTransaction/userSelect.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        stage.setTitle("Undo Transaction");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * restocks cash inventory
     * @throws IOException throws IOException when loading new scene
     */
    @FXML
    public void restock() throws IOException {
        System.out.println("restock");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("restock/displayAddFive.fxml"));
        loader.load();
        AddFiveController fiveController = loader.getController();
        fiveController.setup();
        Parent root = loader.getRoot();
        stage.setTitle("Restock - Add Five");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * manager's option to set the date of the ATM
     * @throws IOException throws IOException when loading new scene
     */
    @FXML
    public void setDate() throws IOException {
        System.out.println("setDate");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displaySetDate.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Set Date");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * promoting tellers if they have enough experience points
     * @throws IOException when loading new scene
     */
    @FXML
    public void promote() throws IOException{
        System.out.println("promote");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setupTeller/displayReqTeller.fxml"));
        loader.load();
        DisplayReqTellerController displayReqTellerController = loader.getController();
        displayReqTellerController.getTeller();
        Parent root = loader.getRoot();
        stage.setTitle("Set Date");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * log out, save the clientmanager and close the scene
     * @throws IOException for closing scene
     */
    @FXML
    public void logout()throws IOException{
        clientManager.saveMap(clientManager.getPath());
        clientManager.loadClientManager();
        System.out.println(clientManager.getUserList());
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();

    }

    /**
     * setup new user that wish to create a new user
     * @throws IOException for loading scenes
     */
    @FXML
    public void setupUser() throws IOException {
        System.out.println("setupUSER");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setupUser/displayReqUser.fxml"));
        loader.load();
        DisplayReqUserController displayReqUserController = loader.getController();
        displayReqUserController.getAccounts();
        Parent root = loader.getRoot();
        stage.setTitle("Requested Users");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
