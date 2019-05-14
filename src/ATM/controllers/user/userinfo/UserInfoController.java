package controllers.user.userinfo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.clients.ClientManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Create a new scene for user info
 */
public class UserInfoController implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button netTotalButton;
    @FXML
    private Button recentTransactionsButton;
    @FXML
    private Button accountSummaryButton;
    @FXML
    private Button creationDateButton;

    /**
     * Back button
     */
    @FXML
    public void returnButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Display accounts net total
     */
    @FXML
    public void netTotal() throws IOException {
        Stage stage = (Stage) netTotalButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayNetTotal.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info - Net Total");
        stage.setResizable(false);

        NetTotalController netTotalController = loader.getController();
        netTotalController.setNetText(ClientManager.loggedInUser);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Display recent transactions
     */
    @FXML
    public void recentTransactions() throws IOException {
        Stage stage = (Stage) recentTransactionsButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayRecentTransactions.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info - Recent Transactions");
        stage.setResizable(false);

        RecentTransactionsController recentTransactionsController = loader.getController();
        recentTransactionsController.setTransactions(ClientManager.loggedInUser);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Display user's account summary
     */
    @FXML
    public void accountSummary() throws IOException {
        Stage stage = (Stage) accountSummaryButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayAccountSummary.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        stage.setTitle("User Info - Account Summary");
        stage.setResizable(false);

        AccountSummaryController accountSummaryController = loader.getController();
        accountSummaryController.setAccountSummary(ClientManager.loggedInUser);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Display account creation date
     */
    @FXML
    public void creationDate() throws IOException {
        Stage stage = (Stage) creationDateButton.getScene().getWindow();
        stage.close();

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayUserInfoAccounts.fxml"));
        loader.load();
        UserInfoAccountsController userInfoAccountsController = loader.getController();
        userInfoAccountsController.setAccounts();
        Parent root = loader.getRoot();

        stage.setTitle("User Info - Account Creation Date");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Overriding interface method
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
