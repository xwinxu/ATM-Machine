package controllers.user.changepass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.clients.ClientManager;
import main.utilities.InputChecker;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassController implements Initializable {

    //Initialized Attribute.
    private InputChecker inputChecker = new InputChecker();

    //FXML Attributes.
    @FXML
    private TextField currpass;
    @FXML
    private TextField newpass;
    @FXML
    private TextField confirmpass;
    @FXML
    private Label mssg;
    @FXML
    private Button exitButton;

    /**
     * Checks and changes user password.
     */
    @FXML
    public void changePass(){
        System.out.println("pressed");
        if(currpass.getText().equals("") || newpass.getText().equals("")||confirmpass.getText().equals("")){
            mssg.setText("Please fill out all of the boxes above");
        }else if(!inputChecker.validPassword(newpass.getText())) {
            mssg.setText("Password must contain at least 6 characters, \n an uppercase letter and a number");

        }else {
            mssg.setText("");
            if(currpass.getText().equals(ClientManager.loggedInUser.getPassword())){
                if(!newpass.getText().equals(currpass.getText())){
                    if(newpass.getText().equals(confirmpass.getText())){
                        ClientManager.loggedInUser.setPassword(newpass.getText());
                        Stage stage = (Stage) exitButton.getScene().getWindow();
                        stage.close();
                    }
                }
            }
            mssg.setText("Invalid information. Password Reset Failed.");
        }

    }

    /**
     * Overrides Initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
