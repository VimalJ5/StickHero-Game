package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;

public class Controller implements Initializable {
    private Stage stage;

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    private Scene gameScene;
    private Parent root;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Rectangle tower1;
    @FXML
    private Text banana;
    private MediaPlayer startmediaPlayer;
    public void startPosition() {
        tower1.setX(610);
        tower1.setY(470);
    }

    public void testing(ActionEvent event) {
        System.out.println("Working");
    }

    public void gameSceneShift(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startmediaPlayer.stop();;
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("GameScene.fxml"));
        //FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("GameScene - Copy.fxml"));
        Parent root = loader.load();

        PlayingController playingController = loader.getController();
        playingController.setStage(stage, 0);
        playingController.setGameScene(gameScene);

        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);
        stage.setResizable(true);
        stage.show();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        banana.setText(Integer.toString(Banana.getBanana_count()));
        startPosition();
        File mediafile=new File("src/main/resources/com/example/stickhero/start.mp3");
        Media start=new Media(mediafile.toURI().toString());
        startmediaPlayer=new MediaPlayer(start);
        startmediaPlayer.play();
    }
}