package controllers.user.accountrequest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.accounts.AccountType;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.Currency;
import java.util.ResourceBundle;

public class SelectCurrencyController implements Initializable {
    //Initialized Attributes.
    public AccountType requestType;
    ClientManager clientManager = ClientManager.getInstance();
    Currency currency;

    //FXML Attributes.
    @FXML
    private Button usd;
    @FXML
    private Button cad;
    @FXML
    private Button eur;
    @FXML
    private Button exitButton;

    /**
     * Sets overseas account to have USD currency type.
     */
    @FXML
    public void setUsd() throws IOException{
        createOverseas("USD");
    }

    /**
     * Sets overseas account to have CAD currency type.
     */
    @FXML
    public void setCad()throws IOException{
        createOverseas("CAD");
    }

    /**
     * Sets overseas account to have EUR currency type.
     */
    @FXML
    public void setEur()throws IOException{
        createOverseas("EUR");
    }

    /**
     * Creates an overseas account.
     */
    public void createOverseas(String currType) throws IOException {

        ClientManager.loggedInUser.requestNewAccount(requestType,currType);
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

    /**
     * Returns to previous scene.
     */
    @FXML
    public void returnButton(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
