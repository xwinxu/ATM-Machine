package controllers.admin.restock;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.clients.ClientManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller class for displaying the gui that prompts user for adding 50 dollar bills
 */
public class AddFiftyController implements Initializable {
    //items in fxml scene that deals with user input output
    @FXML
    private Button addButton;
    @FXML
    private TextField depositAmount;
    @FXML
    private Label curAmount;

    /**
     * setup method that initializes fields before the scene is built
     */
    public void setup(){
        int amount = ClientManager.loggedInManager.getCurrencyAmount("50");
        curAmount.setText(Integer.toString(amount));
    }

    /**
     * addFifty takes user's deposit amount and restocks the number of bills into the machine. Then closes the scene
     */
    public void addFifty(){
        ClientManager.loggedInManager.restock("50", Integer.parseInt(depositAmount.getText()));
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    /**
     * overriding abstract method initialize from the Initializable interface
     * @param url url parameter
     * @param rb resource bundle  parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
