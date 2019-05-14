package controllers.user.userinfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.clients.User;
import main.utilities.Transaction;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Create a new recent transaction scene
 */
public class RecentTransactionsController implements Initializable {
    @FXML
    private Button exitButton;

    @FXML
    private Label recTrans;

    /**
     * Display all user's recent transactions
     * @param user  the logged in user
     */
    @FXML
    public void setTransactions(User user) {
        List<Transaction> allTransactions = user.getTransactions();
        int numOfTransactions = Math.min(10,allTransactions.size());
        List<Transaction> recentTransactions = allTransactions.subList(allTransactions.size() - numOfTransactions,
                allTransactions.size());
        System.out.println(recentTransactions);
        recTrans.setText("Receipt printed. See below.");
    }

    /**
     * Back option
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
     * Overridden interface method
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
