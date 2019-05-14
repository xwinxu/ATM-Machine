package controllers.teller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A scene for teller promotion request
 */
public class RequestPromotionController implements Initializable {

    @FXML
    private Button returnButton;

    /**
     * Overbidding interface methods
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void returnButton() throws IOException {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();

    }
}
