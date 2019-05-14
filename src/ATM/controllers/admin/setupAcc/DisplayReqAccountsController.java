package controllers.admin.setupAcc;

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
import main.clients.UserRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DisplayReqAccountsController class for displaying the requested accounts by users for a logged in manager
 */
public class DisplayReqAccountsController implements Initializable {
    //fields that must be assigned before drawing the new scene
    private ClientManager clientManager = ClientManager.getInstance();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Button> pressedButtons = new ArrayList<Button>();

    //fxml items in the displayReqAccounts.fxml
    @FXML
    private Button confirmButton;
    @FXML
    private VBox vBox;
    @FXML
    private Pane pane;

    /**
     * button listener for confirming all the pressed accounts
     */
    @FXML
    public void confirmAccounts() {
        for (Button button : buttons) {
            if (button.getStyle().equals("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")) {
                UserRequest request = (UserRequest) button.getUserData();
                ClientManager.loggedInManager.registerAccount(request);
            }
        }
        clientManager.clearAccountRequestList();
        clientManager.saveMap(clientManager.getPath());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * gets the list of requested accounts before drawing the scene and creates new buttons with the text displaying
     * each request.
     */
    public void getAccounts() {
        int x = 0;
        int y = 0;
        System.out.println(clientManager.getAccountRequestList().size());
        if (clientManager.getAccountRequestList() == null) {
            System.out.println("pendingAccountList is null");
        } else {
            for (UserRequest request : clientManager.getAccountRequestList()) {
                final Button button;
                User other = request.getOther();
                User user = request.getUser();
                if (request.isJoint()) {
                    button = new Button(other.getUsername() + "&" + user.getUsername() + " " +
                            request.getRequestType());
                } else {
                    button = new Button(user.getUsername() + " " + request.getRequestType());
                }
                button.setUserData(request);
                button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: #1971e5; "
                        + "-fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                //button listener that changes color of a button when pressed
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (button.getStyle().equals("-fx-background-color: #031c44; -fx-background-radius: 20;" +
                                " -fx-border-color: " + "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; " +
                                "-fx-text-fill: white")) {
                            button.setStyle("-fx-background-color: #1971e5; -fx-background-radius: 20; " +
                                    "-fx-border-color: " + "#1971e5; -fx-border-width: 3; -fx-border-radius:" +
                                    " 15; -fx-text-fill: white");
                        } else {
                            button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; " +
                                    "-fx-border-color: #1971e5; " + "-fx-border-width: 3; -fx-border-radius: 15;" +
                                    " -fx-text-fill: white");
                        }
                    }
                });
                pane.getChildren().add(button);
                button.setLayoutX(x);
                button.setLayoutY(y);
                button.setPrefHeight(50);
                button.setPrefWidth(150);
                buttons.add(button);
                if (y < 300) {
                    y += 100;
                } else {
                    x += 200;
                    y = 0;
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
