package controllers.admin.setupTeller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.clients.BankClient;
import main.clients.BankTeller;
import main.clients.ClientManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayReqTellerController implements Initializable {
    //fields that needs to be assigned before drawing a new scene
    private ClientManager clientManager = ClientManager.getInstance();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Button> pressedButtons = new ArrayList<Button>();
    //items in the fxml
    @FXML
    private Button confirmButton;
    @FXML
    private VBox vBox;
    @FXML
    private Pane pane;
    /**
     * button listener that confirms all the tellers that are promoted
     */
    @FXML
    public void confirmTeller(){
        System.out.println(ClientManager.getInstance().getPendTellerList()+"hi");
        for (Button button : buttons ){
            if (button.getStyle().equals("-fx-background-color: #1971e5; -fx-background-radius: 20; -fx-border-color: " +
                    "#1971e5; -fx-border-width: 3; -fx-border-radius: 15; -fx-text-fill: white")){
                BankTeller teller = (BankTeller) button.getUserData();
                ClientManager.loggedInManager.promotePendingTeller(teller);
            }
        }
        ClientManager clientManager = ClientManager.getInstance();
        clientManager.clearPendTellerList();
        clientManager.saveMap(clientManager.getPath());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }

    /**
     * get the list of tellers who requested for promotion and create buttons with text for each requested teller
     */
    public void getTeller() {
        int x = 0;
        int y = 0;
        if (clientManager.getPendTellerList() == null) {
            System.out.println("pendingTellerList is null");
        } else {
            for (BankClient client  : clientManager.getPendTellerList()) {
                BankTeller teller = (BankTeller) client;
                Button button = new Button(teller.getUsername());
                button.setUserData(teller);
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
    //override the initialize method which is abstract in interface Initializable
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
