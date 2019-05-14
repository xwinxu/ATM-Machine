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
 * controller class for displaying the gui that prompts user for adding 10 dollar bills
 */
public class AddTenController implements Initializable {
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
        int amount = ClientManager.loggedInManager.getCurrencyAmount("10");
        curAmount.setText(Integer.toString(amount));
    }

    /**
     * addTen takes user's deposit amount and restocks the number of bills into the machine. Then closes the scene
     * and opens the next addTwentyController's scene
     */
    public void addTen() throws IOException {
        ClientManager.loggedInManager.restock("10", Integer.parseInt(depositAmount.getText()));
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayAddTwenty.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        AddTwentyController twentyController = loader.getController();
        twentyController.setup();
        stage.setTitle("Restock - Add Twenty");
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