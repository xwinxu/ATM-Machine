package controllers.user.transfer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
The scene to decide how to make a transfer
 */
public class TransferOptionController implements Initializable {
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;

    /*
    Logic to make a transfer to oneself
     */
    @FXML
    public void toSelf() throws IOException {
        System.out.println("to self");
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayTransferAccounts.fxml"));
        loader.load();
        DisplayTransferAccountsController displayTransferAccountsController = loader.getController();
        displayTransferAccountsController.setAccounts();
        displayTransferAccountsController.isTransfer = true;
        displayTransferAccountsController.isSelf = true;
        displayTransferAccountsController.transferReceiver = ClientManager.loggedInUser;
        Parent root = loader.getRoot();

        stage.setTitle("Transfer -> Account");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
    Logic to make a transfer to another person
     */
    @FXML
    public void toOther() throws IOException{
        System.out.println("to other");
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayTransferAccounts.fxml"));
        loader.load();
        DisplayTransferAccountsController displayTransferAccountsController = loader.getController();
        displayTransferAccountsController.isTransfer = true;
        displayTransferAccountsController.isSelf = false;
        displayTransferAccountsController.setAccounts();
        Parent root = loader.getRoot();

        stage.setTitle("Transfer -> Other User");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /*
    Logic to pay to an external person
     */
    @FXML
    public void extbill() throws IOException{
        System.out.println("pay bill");
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("paybill.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("Transfer -> Pay Bill");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
