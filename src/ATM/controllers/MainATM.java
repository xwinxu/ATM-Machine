package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.ATMMachine;
import main.clients.ClientManager;
import main.utilities.KeyValueFileHandler;

import java.io.File;

public class MainATM extends Application {
    //File path for cash inventory
    private static final String CASH_INVENTOR_FILENAME = "cash_inventory.txt";
    //default amount of each bill in the cash inventory
    private static final int DEFAULT_AMOUNT = 50;
    private ATMMachine atmMachine;

    MediaPlayer mediaPlayer;

    /**
     * Start up the starting interface for the ATM machine
     * @param stage the current scene
     * @throws Exception exception for errors
     */
    @Override
    public void start(Stage stage) throws Exception {
        //atmMachine.setUp();
        KeyValueFileHandler handler = new KeyValueFileHandler();
        ClientManager clientManager = ClientManager.getInstance();
        atmMachine = new ATMMachine();
        clientManager.loadClientManager();
        Parent root = FXMLLoader.load(getClass().getResource("launchlogin.fxml"));

        atmMachine.setupCash();
        atmMachine.initTime();
        String audioFile = "phase2/src/ATM/controllers/resources/21BankAccount.mp3";
        //String audioFile = "phase2/src/ATM/controllers/resources/ATM.mp3";

        Media audio = new Media(new File(audioFile).toURI().toString());
        mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setAutoPlay(true);

        Scene scene = new Scene(root);
        stage.setResizable(false);

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}