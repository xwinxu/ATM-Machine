package controllers.teller.accountrequest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.accounts.AccountType;
import main.clients.ClientManager;
import main.clients.User;
import main.clients.UserRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * A scene for displaying account requests
 */
public class DisplayReqAccountTeller implements Initializable {
    /**
     * A copy of client manager
     */
    private ClientManager clientManager = ClientManager.getInstance();
    /**
     * A list of buttons for approval
     */
    private ArrayList<Button> buttons = new ArrayList<Button>();
    /**
     * a list of selected buttons
     */
    private ArrayList<Button> pressedButtons = new ArrayList<Button>();

    @FXML
    private Button confirmButton;
    @FXML
    private Pane pane;

    @FXML
    /**
     * Action performed when confirm is pressed
     */
    public void confirmAccount() {
        for (Button button : buttons) {
            if (button.getStyle().equals("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")) {
                UserRequest request = (UserRequest) button.getUserData();
                ClientManager.loggedInTeller.registerAccount(request);
//                clientManager.getAccountRequestList().remove(request);
            }
        }
        clientManager.getAccountRequestList().clear();
        ClientManager clientManager = ClientManager.getInstance();
        clientManager.saveMap(clientManager.getPath());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Get the list of account requests
     */
    public void getTeller() {
        int x = 0;
        int y = 0;
        if (clientManager.getAccountRequestList() == null) {
            System.out.println("accountRequestList is null");
        } else {
            for (UserRequest request : clientManager.getAccountRequestList()) {
                if (request.getRequestType().equals(AccountType.CHEQUING) ||
                        request.getRequestType().equals(AccountType.SAVINGS)) {
                    User user = request.getUser();
                    Button button = new Button(user.getUsername() + " " + request.getRequestType());
                    button.setUserData(request);
                    button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: #1971e5; " +
                            "-fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                    button.setOnAction(event -> {
                        if (button.getStyle().equals("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: " +
                                "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")) {
                            button.setStyle("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
                        } else {
                            button.setStyle("-fx-background-color: #031c44; -fx-background-radius: 20; -fx-border-color: #1971e5; " +
                                    "-fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white");
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
    }

    /**
     * Overridden abstract method for interface
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
