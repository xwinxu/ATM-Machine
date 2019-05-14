package controllers.user.transfer;

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

/*
The scene for the user to whom we will transfer an amount
 */
public class PayBillController implements Initializable {
    @FXML
    private Button displayAccounts;

    @FXML
    public void displayAccounts() throws IOException{

        Stage stage = (Stage) displayAccounts.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayTransferAccounts.fxml"));
        loader.load();
        DisplayTransferAccountsController displayTransferAccountsController = loader.getController();
        displayTransferAccountsController.setAccounts();
        displayTransferAccountsController.isTransfer = false;
        Parent root = loader.getRoot();

        stage.setTitle("Transfer Amount");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
