package controllers.user.userinfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.accounts.BankAccount;
import main.clients.ClientManager;
import main.accounts.BankAccount;
import main.clients.ClientManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Create a new scene for displaying user info
 */
public class UserInfoAccountsController implements Initializable {
    public ArrayList<BankAccount> account = ClientManager.loggedInUser.getAccounts();

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    /**
     * A list of user info options
     */
    public ArrayList<Button> buttons = new ArrayList<>();

    /**
     * Init buttons
     */
    public void setAccounts() {

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);

        for (int i = 0; i < account.size(); i++) {
            if (i < 6) {
                System.out.println(account.get(i).accountID);
                String[] accountID = account.get(i).accountID.split(" ");
                buttons.get(i).setText(accountID[accountID.length - 1]);
            }
        }
    }

    /**
     * Display account 1
     */
    @FXML
    public void account1() throws IOException {
        if (account.size() > 0) {
            displayDate(account.get(0));
        }
    }

    /**
     * Display account 2
     */
    @FXML
    public void account2() throws IOException {
        if (account.size() > 1) {
            displayDate(account.get(1));
        }
    }

    /**
     * Display account 3
     */
    @FXML
    public void account3() throws IOException {
        if (account.size() > 2) {
            displayDate(account.get(2));
        }
    }

    /**
     * Display account 4
     */
    @FXML
    public void account4() throws IOException {
        if (account.size() > 3) {
            displayDate(account.get(3));
        }
    }

    /**
     * Display account 5
     */
    @FXML
    public void account5() throws IOException {
        if (account.size() > 4) {
            displayDate(account.get(4));
        }
    }

    /**
     * Display account 6
     */
    @FXML
    public void account6() throws IOException {
        if (account.size() > 5) {
            displayDate(account.get(5));
        }

    }

    /**
     * Display account creation date
     *
     * @param account the target account
     */
    public void displayDate(BankAccount account) throws IOException {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("displayAccountCreationDate.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        AccountCreationDateController accCreationDate = loader.getController();
        accCreationDate.setCreationDateText(account);

        stage.setTitle("Account Creation Date");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Overriding the interface method
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
