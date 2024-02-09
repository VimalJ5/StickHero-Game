package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class EndgameController implements Initializable {

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Monkey monkey;
    public void setMonkey(Monkey monkey) throws IOException {
        this.monkey = monkey;
        this.score.setText(Integer.toString(monkey.getScore()));
        if(Monkey.getBest_Score() < monkey.getScore())
        {
            Monkey.setBest_Score(monkey.getScore());
        }
        this.best_score.setText(Integer.toString(Monkey.getBest_Score()));
        this.banana_score.setText(Integer.toString(Banana.getBanana_count()));
        PrintWriter out=null;
        try {
            out = new PrintWriter(new FileWriter("src/main/resources/com/example/stickhero/score.txt"));
            out.write(Integer.toString(Monkey.getBest_Score()).concat("\n"));
            out.write(Integer.toString(Banana.getBanana_count()));
        }finally{
            if(out!=null){
                out.close();
            }
        }
    }

    private Stage stage;
    private Scene gameScene;
    private Parent root;


    @FXML
    private Rectangle tower1;
    @FXML
    private Rectangle tower2;

    @FXML
    private Text score;
    @FXML
    private Text banana_score;
    @FXML
    private Text best_score;
    @FXML
    private Text warning;

    @FXML
    private ImageView monkeyImageView;
    private MediaPlayer endmediaPlayer;
    private MediaPlayer gamemediaPlayer;
    private MediaPlayer startmediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File mediafile=new File("src/main/resources/com/example/stickhero/end.mp3");
        Media start=new Media(mediafile.toURI().toString());
        endmediaPlayer=new MediaPlayer(start);
        endmediaPlayer.play();
    }

    public void song_play()
    {
        File mediafile=new File("src/main/resources/com/example/stickhero/end.mp3");
        Media start=new Media(mediafile.toURI().toString());
        endmediaPlayer=new MediaPlayer(start);
        endmediaPlayer.play();
    }



    public void menuSceneShift(MouseEvent event) throws IOException {
        endmediaPlayer.stop();
        warning.setVisible(false);

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Homepage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameScene = new Scene(root);
        stage.setScene(gameScene);
        stage.show();
    }

    public void gameSceneShift(MouseEvent event) throws IOException {
        endmediaPlayer.stop();
        warning.setVisible(false);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("GameScene.fxml"));
        Parent root = loader.load();

        PlayingController playingController = loader.getController();
        playingController.setStage(stage,0);

        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);
        stage.setResizable(true);
        stage.show();
    }

    public void Revive(MouseEvent event) throws IOException {
        endmediaPlayer.stop();
        if(Banana.getBanana_count() >= 5) {
            Banana.setBanana_count(Banana.getBanana_count() - 5);

            PrintWriter out=null;
            try {
                out = new PrintWriter(new FileWriter("src/main/resources/com/example/stickhero/score.txt"));
                out.write(Integer.toString(Monkey.getBest_Score()).concat("\n"));
                out.write(Integer.toString(Banana.getBanana_count()));
            }finally{
                if(out!=null){
                    out.close();
                }
            }

            warning.setVisible(false);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("GameScene.fxml"));
            Parent root = loader.load();

            PlayingController playingController = loader.getController();
            playingController.setStage(stage, monkey.getScore());

            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setResizable(true);
            stage.show();
        } else {
                warning.setVisible(true);
        }

    }
}