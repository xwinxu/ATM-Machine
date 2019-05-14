package controllers;

import controllers.admin.AdminLoginController;
import controllers.user.UserLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.clients.*;
import main.utilities.InputChecker;
import main.utilities.KeyValueFileHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    //Initialized Attributes.
    public ClientManager clientManager = ClientManager.getInstance();
    public InputChecker inputChecker = new InputChecker();
    public String username;
    public String userpass;

    //FXML Attributes.
    @FXML
    private TextField useridtf;
    @FXML
    private Label mssg;
    @FXML
    private Button loginb;
    @FXML
    private Button createb;
    @FXML
    private PasswordField passwordtf;
    @FXML
    private RadioButton userrb;
    @FXML
    private ToggleGroup UserOrAdmin;
    @FXML
    private RadioButton adminrb;
    @FXML
    private RadioButton tellerb;

    /**
     * Creates a request for new user.
     */
    @FXML
    private void createClient() {
        username = useridtf.getText();
        userpass = passwordtf.getText();
        String clientType = "";
        boolean checkInput = checkInput();
        if (userrb.isSelected() && checkInput) {
            mssg.setText("");
            clientType = "pendUser";
            User user = new User(username, userpass);
            createClient(clientType, user);
            mssg.setText("Requested. Please wait for manager to approve");
        } else if (adminrb.isSelected()) {
            mssg.setText("Sorry, you cannot create a manager");
        } else if (tellerb.isSelected()) {
            mssg.setText("Sorry, you cannot create a teller");
        }
        useridtf.clear();
        passwordtf.clear();
        clientManager.saveMap(clientManager.getPath());
        clientManager.loadClientManager();
    }

    /**
     * Verify login function for clients.
     */
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        clientManager.loadClientManager();
        clientManager = ClientManager.getInstance();
        mssg.setText("");
        username = useridtf.getText();
        userpass = passwordtf.getText();
        if (checkFieldFilled(username, userpass)) {
            if (userrb.isSelected()) {
                mssg.setText("");
                String clientType = "user";
                boolean success = checkLogin(clientType, username, userpass);
                if (success) {
                    setNewScene("user/userlogin.fxml", "user");
                } else {
                    mssg.setText("login failed: Please enter an correct username and password");
                }
            } else if (adminrb.isSelected()) {
                mssg.setText("");
                String clientType = "manager";
                boolean success = checkLogin(clientType, username, userpass);
                if (success) {
                    setNewScene("admin/adminlogin.fxml", "manager");
                } else {
                    mssg.setText("login failed: Please enter an correct username and password");
                }
            } else {
                mssg.setText("");
                String clientType = "teller";
                boolean success = checkLogin(clientType, username, userpass);
                if (success) {
                    setNewScene("teller/tellerlogin.fxml", "teller");
                } else {
                    mssg.setText("login failed: Please enter an correct username and password");
                }
            }
            useridtf.setText("");
            passwordtf.setText("");
        }
    }

    /**
     * Checks field input.
     */
    private boolean checkInput(){
        if(checkFieldFilled(username, userpass)){
            if(checkValidUserName(username)){
                return (checkPassValid(userpass));
            }
        }
        return false;
    }

    /**
     * Checks if all fields are filled.
     */
    private boolean checkFieldFilled(String username, String password) {
        if (username.equals("") || password.equals("")) {
            mssg.setText("Please enter username and password");
            return false;
        }
        return true;
    }

    /**
     * Verifies password.
     */
    private boolean checkPassValid(String password) {
        if (!inputChecker.validPassword(passwordtf.getText())) {
            mssg.setText("Password must contain at least 6 characters, \n an uppercase letter and a number");
            return false;
        }
        return true;
    }

    /**
     * Verifies Username.
     */
    private boolean checkValidUserName(String username) {
        if (clientManager.isExistingClient(username)) {
            mssg.setText("Username is taken.");
            return false;
        }
        return true;
    }

    /**
     * Creates a new client.
     */
    private void createClient(String clientType,BankClient bankClient){
        clientManager.addToMasterList(clientType, bankClient);
        clientManager.saveMap(clientManager.getPath());
    }

    /**
     * Checks login information.
     */
    private boolean checkLogin(String clientType, String username, String password)throws IOException {
        System.out.println(clientManager.masterList.get("user"));
        for (BankClient client : clientManager.getClientList(clientType)) {
            if (username.equals(client.getUsername())) {
                if (password.equals(client.getPassword())) {
                    ClientManager.loggedInClient = client;
                    if(clientType.equals("user")) {
                        ClientManager.loggedInUser = (User) client;
                    }else if(clientType.equals("manager")){
                        ClientManager.loggedInManager = (BankManager) client;
                    }else{
                        ClientManager.loggedInTeller = (BankTeller) client;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Opens a new scene.
     */
    private void setNewScene(String resource, String clientType) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resource));
        loader.load();
        setUpController(loader,clientType);

    }

    /**
     * Sets up ATM files.
     */
    private void setUpController(FXMLLoader loader,String clientType ) {
        KeyValueFileHandler handler = new KeyValueFileHandler();
        String date = handler.getFileValue("config.txt", "date");
        if (clientType.equals("manager")){
            AdminLoginController controller = loader.getController();
            controller.setUpText(ClientManager.loggedInManager.getUsername(),date);
        }else if (clientType.equals("user")){
            UserLoginController controller = loader.getController();
            controller.setUpText(useridtf.getText(), date, ClientManager.loggedInUser.getAccounts());
        }else{
            TellerLoginController controller = loader.getController();
            controller.setUp(useridtf.getText(),date,ClientManager.loggedInTeller.getAccounts());
        }
        setNewStage(loader);
    }

    /**
     * Sets new stage.
     */
    private void setNewStage(FXMLLoader loader){
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("User Page");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        mssg.setText("");
        useridtf.clear();
        passwordtf.clear();
    }

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
