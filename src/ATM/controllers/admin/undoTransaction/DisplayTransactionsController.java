package controllers.admin.undoTransaction;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.clients.ClientManager;
import main.clients.User;
import main.utilities.Transaction;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**Controller for displaying all the tran
 *
 */
public class DisplayTransactionsController implements Initializable {
    private ClientManager clientManager = ClientManager.getInstance();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Button> pressedButtons = new ArrayList<Button>();
    private User user;

    @FXML
    private Button confirmButton;
    @FXML
    private VBox vBox;
    @FXML
    private Pane pane;

    /**
     *
     */
    @FXML
    public void confirmUndo(){
        for (Button button : buttons ){
            if (button.getStyle().equals("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")){
                Transaction transaction = (Transaction) button.getUserData();
                transaction.undoTransaction(user);
            }
        }
        buttons.clear();
        clientManager.saveMap(clientManager.getPath());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }

    /**
     *
     * @param user
     */
    public void getTransaction(User user) {
        this.user = user;
        int x = 0;
        int y = 0;
        if (user.getTransactions() == null) {
            System.out.println("No transactions");
        } else {
            List<Transaction> allTransactions = user.getTransactions();
            System.out.println("ALL TRANSACTIONS");
            System.out.println(allTransactions);
            int numOfTransactions = Math.min(10,allTransactions.size());
            List<Transaction> recentTransactions = allTransactions.subList(allTransactions.size() - numOfTransactions,
                    allTransactions.size());
            System.out.println("RECENT TRANSACTIONS");
            System.out.println(recentTransactions);
            for (Transaction transaction  : recentTransactions) {
                if(!(transaction.toString().contains("UNDO")) && !transaction.hasUndo) {
                    Button button = new Button(transaction.toString());
                    button.setUserData(transaction);
                    button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: #1971e5; " +
                            "-fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (button.getStyle().equals("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: " +
                                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")) {
                                button.setStyle("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                                        "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                            } else {
                                button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: #1971e5; " +
                                        "-fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                            }
                        }

                    });
                    pane.getChildren().add(button);
                    button.setLayoutX(x);
                    button.setLayoutY(y);
                    button.setPrefHeight(80);
                    button.setPrefWidth(200);
                    buttons.add(button);
                    if (y < 300) {
                        y += 100;
                    } else {
                        x += 230;
                        y = 0;
                    }
                }

            }
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
