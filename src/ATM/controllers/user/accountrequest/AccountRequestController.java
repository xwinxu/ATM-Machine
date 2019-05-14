package controllers.user.accountrequest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.AccountType;
import main.clients.ClientManager;
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountRequestController implements Initializable {

    // Initialized attributes.
    private ClientManager clientManager = ClientManager.getInstance();
    public User otherUser;
    public boolean isJoint;
    public String requestType;

    // FXML attributes.
    @FXML
    private Label mssg;
    @FXML
    private Button exitButton;
    @FXML
    private Button savingsButton;
    @FXML
    private Button creditCardButton;
    @FXML
    private Button lineOfCreditButton;
    @FXML
    private Button overseasButton;
    @FXML
    private Button chequingButton;

    /**
     * Return to the previous scene.
     */
    @FXML
    public void returnButton(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Requests credit card account.
     */
    @FXML
    public void creditCardSelection() throws IOException{
        requestNewAccount(AccountType.CREDIT);

    }

    /**
     * Requests line of credit account.
     */
    @FXML
    public void lineOfCreditSelection() throws IOException{
        requestNewAccount(AccountType.LINE_CREDIT);

    }

    /**
     * Requests savings account.
     */
    @FXML
    public void savingsSelection() throws IOException{
        requestNewAccount(AccountType.SAVINGS);

    }

    /**
     * Requests chequing account.
     */
    @FXML
    public void chequingSelection() throws IOException{
        requestNewAccount(AccountType.CHEQUING);

    }

    /**
     * Requests overseas account.
     */
    @FXML
    public void overseasSelection() throws IOException{
        requestNewAccount(AccountType.OVERSEAS);

    }

    /**
     * Sets if an account request should be a joint account request.
     */
    public void setIsJoint(boolean joint){
        isJoint = joint;
    }


    /**
     * Requests a new account.
     */
    public void requestNewAccount(AccountType requestType) throws IOException {
        User user = ClientManager.loggedInUser;
        System.out.println(user.getAccounts());
        if (user.getAccounts().size() >= 6) {
            mssg.setText("Already has max number of accounts(6)");
        } else {
            if (requestType.equals(AccountType.OVERSEAS)) {
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
                clientManager.saveMap(clientManager.getPath());
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("selectCurrency.fxml"));
                loader.load();
                SelectCurrencyController selectCurrencyController = loader.getController();
                selectCurrencyController.requestType = requestType;
                Parent root = loader.getRoot();

                stage.setTitle("Request New Account");
                stage.setResizable(false);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                if (isJoint) {
                    ClientManager.loggedInUser.requestNewAccount(requestType, otherUser);

                } else {
                    ClientManager.loggedInUser.requestNewAccount(requestType);
                    System.out.println("Requests: " + ClientManager.loggedInUser.getUserRequest());

                }

                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
                clientManager.saveMap(clientManager.getPath());
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("sentRequest.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                stage.setTitle("Request New Account");
                stage.setResizable(false);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Overrides initialize.
     *
     * @param url URL parameter.
     * @param rb ResourceBundle parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
