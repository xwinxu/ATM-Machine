package controllers.admin.setupUser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.clients.BankClient;
import main.clients.ClientManager;
import main.clients.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayReqUserController implements Initializable {
    private ClientManager clientManager = ClientManager.getInstance();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Button> pressedButtons = new ArrayList<Button>();

    @FXML
    private Button confirmButton;
    @FXML
    private VBox vBox;
    @FXML
    private Pane pane;
    @FXML
    public void confirmUser(){
        for (Button button : buttons ){
            if (button.getStyle().equals("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")){
                User user = (User) button.getUserData();
                clientManager.addToMasterList("user",user);
                System.out.println(clientManager.getUserList() + "123" + user);
            }
        }
        clientManager.clearPendUserList();
        buttons.clear();
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }
    public void getAccounts() {
        int x = 0;
        int y = 0;
        if (clientManager.getPendUserList() == null) {
            System.out.println("pendingUserList is null");
        } else {
            for (BankClient client  : clientManager.getPendUserList()) {
                User user = (User)client;
                Button button = new Button(user.getUsername());
                button.setUserData(user);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
