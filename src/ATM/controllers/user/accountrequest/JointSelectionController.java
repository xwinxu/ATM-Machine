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

public class JointSelectionController implements Initializable{
    // FXML Attributes.
    @FXML
    private Button exitButton;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    /**
     * Returns to previous scene.
     */
    @FXML
    public void returnButton(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    /**
     * requests joint account.
     */
    @FXML
    public void yesJoint() throws IOException {
        Stage stage = (Stage) yesButton.getScene().getWindow();
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

    /**
     * Requests account to not be joint.
     */
    @FXML
    public void noJoint() throws IOException{
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("accountRequest.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Request New Account");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Overrides initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
