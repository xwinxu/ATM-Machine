package controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.clients.BankClient;
import main.clients.ClientManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PromoteTeller implements Initializable {
    @FXML
    private Button promoteb;

    @FXML
    private Button denyb;

    @FXML
    private Label tellerid;

    @FXML
    private Label expfield;

    ClientManager clientManager = ClientManager.getInstance();

    public void promote() {
        for (BankClient t : clientManager.getPendTellerList()) {

            Stage stage = (Stage) promoteb.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
