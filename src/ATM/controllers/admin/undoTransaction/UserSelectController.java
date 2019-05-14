package controllers.admin.undoTransaction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.clients.BankClient;
import main.clients.ClientManager;
import main.clients.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserSelectController implements Initializable {
    //fields that must be assigned before drawing the new scene
    private User user;
    //items in userSelect.fxml
    @FXML
    private Label mssg;
    @FXML
    private TextField getOtherUser;


    /**
     * checkUserExists returns true if there is a user with the username
     */
    @FXML
    private boolean checkUserExists() {
        boolean userExists = false;
        ClientManager clientManager = ClientManager.getInstance();
        ArrayList<BankClient> list = clientManager.getUserList();
        list.addAll(clientManager.getTellerList());
        for (BankClient client : list) {
            if (client.getUsername().equals(getOtherUser.getText())) {
                userExists = true;
                user = (User) client;
            }
        }
        return userExists;
    }

    /**
     * button listener for going on to the next stage
     * @throws IOException for loading new stage
     */
    public void go() throws IOException {
        boolean userExists = checkUserExists();
        if (userExists) {
            Stage stage = (Stage) getOtherUser.getScene().getWindow();
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("displayTransaction.fxml"));
            loader.load();
            DisplayTransactionsController controller = loader.getController();
            controller.getTransaction(user);
            Parent root = loader.getRoot();

            stage.setTitle("Transfer Amount");
            stage.setResizable(false);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            mssg.setText("Cannot select current user. Please input a valid user");
        }

    }

    /**
     * overriding abstract method initialize from the Initializable interface
     * @param url url parameter
     * @param rb resource bundle  parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
