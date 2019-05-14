package controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.clients.ClientManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * set date controller class for setting the date fxml
 */
public class SetDateController implements Initializable {
    //items in the fxml file
    @FXML
    private Button confirmButton;
    @FXML
    private TextField enterDate;
    @FXML
    private Label curdate;

    /**
     * sets the curr date to the date and close the scene
     */
    public void confirm() {
        String d = enterDate.getText();
        LocalDate date = LocalDate.parse(d);
        ClientManager.loggedInManager.setDate(date);
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    //override the initialize method which is abstract in interface Initializable
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
