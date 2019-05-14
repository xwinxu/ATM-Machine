package controllers.user.userinfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.UIOptions.user.UserUI;
import main.UIOptions.user.UserUIOption;
import main.accounts.BankAccount;
import main.clients.User;
import main.UIOptions.user.UserUI;
import main.UIOptions.user.UserUIOption;
import main.clients.User;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * NetTotalController class for the fxml that displays the net total of a user
 */
public class NetTotalController implements Initializable {
    //items in the fxml
    @FXML
    private Button exitButton;
    @FXML
    private Label netText;
    //fields that must be assigned before drawing the current scene
    public User user;
    public UserUI ui;
    public UserUIOption options;

    /**
     * sets the text to display the net total
     * @param user the user that is logged in
     */
    public void setNetText(User user) {
        long debtAmount = 0;
        long assetAmount = 0;
        ArrayList<BankAccount> accounts = user.getAccounts();
        for(BankAccount account : accounts) {
            if (account.getIsDebt()) {
                assetAmount += account.getBalanceDouble();
            } else {
                debtAmount += account.getBalanceDouble();
            }
        }
        long total = assetAmount - debtAmount;
        DecimalFormat twoDecimal = new DecimalFormat("0.00");
        netText.setText("Your net total: $" + twoDecimal.format(total));
    }

    /**
     * returns to the previous scene by opening the displayUserInfoOptions.fxml and closing the current scene
     * @throws IOException when loading the new scene
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

    /**
     *override the abstract method from the Initializable interface
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
