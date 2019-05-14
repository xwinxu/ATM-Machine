package controllers.user.accountrequest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SentRequestController implements Initializable {
    //FXML Attributes.
    @FXML
    private Button exitButton;

    /**
     * Returns to the previous scene.
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
