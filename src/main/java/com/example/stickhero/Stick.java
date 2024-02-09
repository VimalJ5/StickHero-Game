package com.example.stickhero;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Stick {
    private Stage stage;
    private AnchorPane gamepane;
    private double height;
    private double width;
    private Rectangle stick;
    private double X;
    private double Y;
    private Timeline stickgrowing;
    private Timeline stickfalling;
    private boolean condition = true;

    public Stick(Rectangle stick, AnchorPane gamepane, Stage stage)
    {
        this.gamepane=gamepane;
        this.stick = stick;
        this.stage = stage;

    }

    public void StickGrowTimeline(Monkey monkey, Stick stick, Towers towers, Banana banana)
    {
        AtomicBoolean cond = new AtomicBoolean(true);
        System.out.println("Start grow");
        File mediafile=new File("src/main/resources/com/example/stickhero/growth.mp3");
        Media start=new Media(mediafile.toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(start);
        mediaPlayer.play();
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, stickgrowth);
        stage.getScene().addEventFilter(KeyEvent.KEY_RELEASED,stickgrowthstop);
        stickgrowing = new Timeline(new KeyFrame(Duration.seconds(0.001), event -> {
            if(condition)
            {
                //System.out.println("Testt");
            } else {
                mediaPlayer.stop();
                stage.getScene().removeEventFilter(KeyEvent.KEY_PRESSED,stickgrowth);
                stage.getScene().removeEventFilter(KeyEvent.KEY_RELEASED,stickgrowthstop);
                stop_growth(monkey, stick, towers, banana);
            }
        }));

        stickgrowing.setCycleCount(Timeline.INDEFINITE);
        stickgrowing.play();
    }

    private final EventHandler<KeyEvent> stickgrowth = keyEvent -> {
        if (keyEvent.getCode().equals(KeyCode.UP)) {
            //System.out.println("GROWTHHHHH");
            stick.setY((stick.getY() - 10));
            stick.setHeight(stick.getHeight() + 10);
        }
    };

    private final EventHandler<KeyEvent> stickgrowthstop = keyEvent -> {
        if (keyEvent.getCode().equals(KeyCode.UP)) {
            //System.out.println("CIRCUMCISED");
            condition = false;
        }
    };

    private void stop_growth(Monkey monkey, Stick stick, Towers towers, Banana banana)  {
        stickgrowing.stop();
        System.out.println("Stop grow");
        condition = true;
        monkey.monkeypunching(monkey, stick, towers, banana);
    }

    public void stickFalling(Monkey monkey, Stick stick, Towers towers, Banana banana)
    {
        System.out.println("Start fall");
        Rotate rotate=new Rotate();
        rotate.setAngle(1);
        rotate.setPivotX(stick.getStick().getX());
        rotate.setPivotY(465);
        final double[] angle = {0};
        File mediafile=new File("src/main/resources/com/example/stickhero/whoosh.mp3");
        Media start=new Media(mediafile.toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(start);
        mediaPlayer.play();

        stickfalling = new Timeline(new KeyFrame(Duration.seconds(0.009), event -> {

            if(angle[0] <90)
            {
                stick.getStick().getTransforms().add(rotate);
                angle[0]++;
            } else {
                stop_fall(monkey,stick,towers, banana);
                mediaPlayer.stop();
            }

        }));
        stickfalling.setCycleCount(Timeline.INDEFINITE);
        stickfalling.play();
    }

    private void stop_fall(Monkey monkey, Stick stick, Towers towers, Banana banana) {
        System.out.println("Stop fall");
        stickfalling.stop();
        monkey.monkeyWalking(monkey, stick,towers, banana);

    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle getStick() {
        return stick;
    }

    public void setStick(Rectangle stick) {
        this.stick = stick;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
