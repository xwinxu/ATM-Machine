package controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * CIController class for cash inventory fxml
 */
public class CIController implements Initializable {
    //item in fxml
    @FXML
    private Button exitButton;
    //return to the previous scene by closing the current scene
    public void returnToPrev(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    //override the abstract method from the Initializable interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
