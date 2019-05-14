package controllers.user.accountrequest;

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

public class InvalidUsernameController implements Initializable {
    //FXML Attributes.
    @FXML
    private Button leaveButton;
    @FXML
    private Button retryButton;

    /**
     * Returns to previous scene.
     */
    @FXML
    public void returnButton() {
        Stage stage = (Stage) leaveButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Opens.
     */
    @FXML
    public void retry() throws IOException {
        Stage stage = (Stage) retryButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("jointUsername.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Request New Account");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
