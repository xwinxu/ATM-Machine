package controllers.user.userinfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.accounts.BankAccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AccountCreationDateController class for the fxml that displays the creation date for each account
 */
public class AccountCreationDateController implements Initializable {
    public BankAccount account;
    //items in the fxml file
    @FXML
    private Button exitButton;
    @FXML
    private Label acc;
    @FXML
    private Label creationDate;

    /**
     * sets the creationdate text before the scene is built
     * sets this.account = account
     * @param account the account that is chosen to display the creation date
     */
    public void setCreationDateText(BankAccount account) {
        this.account = account;
        acc.setText(account.toString());
        creationDate.setText(" " + this.account.getCreationDate());
    }

    /**
     * returns to the previous scene by closing the current scene and opening the displayUserInfoOptions.fxml
     * @throws IOException when loading new scene
     */
    @FXML
    public void returnButton() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayUserInfoOptions.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //override the abstract method from the Initializable interface
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
