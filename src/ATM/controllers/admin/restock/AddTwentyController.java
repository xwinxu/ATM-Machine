package controllers.admin.restock;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller class for displaying the gui that prompts user for adding 20 dollar bills
 */
public class AddTwentyController implements Initializable {
    //items in fxml scene that deals with user input output
    @FXML
    private Button addButton;
    @FXML
    private Label curAmount;
    @FXML
    private TextField depositAmount;

    /**
     * setup method that initializes fields before the scene is built
     */
    public void setup(){
        int amount = ClientManager.loggedInManager.getCurrencyAmount("20");
        curAmount.setText(Integer.toString(amount));
    }

    /**
     * addTwenty takes user's deposit amount and restocks the number of bills into the machine. Then closes the scene
     * and opens the next addFiftyController's scene
     */
    public void addTwenty() throws IOException {
        ClientManager.loggedInManager.restock("20", Integer.parseInt(depositAmount.getText()));
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayAddFifty.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        AddFiftyController fiftyController = loader.getController();
        fiftyController.setup();
        stage.setTitle("Restock - Add Fifty");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
