package controllers.teller;

import controllers.teller.accountrequest.DisplayReqAccountTeller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Create a new scene of teller employee options
 */
public class TellerEmployeeOptionsController implements Initializable {
    @FXML
    private Text usernameT;
    @FXML
    private Text dateT;
    @FXML
    private Button logoutButton;

    /**
     * Overriding the interface method
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Set up the teller options
     *
     * @param username the teller's username
     * @param date     the current system date
     */
    public void setUp(String username, String date) {
        usernameT.setText("User: " + username);
        dateT.setText("Date: " + date);

    }

    /**
     * Log out option
     */
    @FXML
    public void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Promotion request
     */
    @FXML
    public void promotionRequest() throws IOException {

        System.out.println("PromotionRequest");

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        ClientManager.loggedInTeller.promotionRequest();
        ClientManager.getInstance().saveMap(ClientManager.getInstance().getPath());
        loader.setLocation(getClass().getResource("displayRequestPromotion.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        stage.setTitle("Request Promotion");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Approve asset acocunt creations
     */
    @FXML
    public void registerAccount() throws IOException {
        System.out.println("RegisterAccount");

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("accountrequest/displayReqAccountTeller.fxml"));
        loader.load();
        DisplayReqAccountTeller displayReqAccountTeller = loader.getController();
        displayReqAccountTeller.getTeller();
        Parent root = loader.getRoot();
        stage.setTitle("Register Account");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
