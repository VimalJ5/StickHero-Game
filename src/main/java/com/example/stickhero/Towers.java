package com.example.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Towers {
    AnchorPane gamepane;

    Stage stage;
    static int count=2;
    Scene scene;
    ActionEvent event;
    private Timeline moveSceneTimeline;
    private Timeline initalTimeline;
    private
    Random rand = new Random();
    public Towers(Stage stage){
        this.stage=stage;
    }
    public Rectangle createTower(double height, double width, double x, double y) {
        Rectangle tower = new Rectangle();
        tower.setWidth(width);
        tower.setHeight(height);
        tower.setX(x);
        tower.setY(y);
        return tower;
    }

    public void setTowerpos(Rectangle tower, Monkey monkey)
    {
        double randomX = 20 + monkey.getCurrentTower().getX() + monkey.getCurrentTower().getWidth() + rand.nextInt(930);
        double randomWidth = 50 + monkey.getMonkeyImageView().getFitWidth() + rand.nextInt(150);

        tower.setWidth(randomWidth);
        tower.setHeight(300);
        tower.setX(randomX);
        tower.setY(470);
        tower.setOpacity(0);
    }

    public void initTimeline(Monkey monkey, Stick stick, Towers towers,Banana banana)
    {
        System.out.println(monkey.getCurrentTower().getX());
        initalTimeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {
            Rectangle tower = monkey.getNextTower();
            setTowerpos(tower,monkey);

            FadeTransition newblockFade = new FadeTransition(Duration.seconds(0.4), tower);
            newblockFade.setToValue(1);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), tower);
            translateTransition.setFromY(tower.getY());
            translateTransition.setToY(0);
            translateTransition.setOnFinished(event1 -> tower.setTranslateY(0));

            ParallelTransition parallelTransition = new ParallelTransition(newblockFade, translateTransition);

            parallelTransition.play();
            parallelTransition.setOnFinished(event2 -> {
                stop_initTimeline(monkey,stick,towers, banana);
            } );
        }));
        initalTimeline.setCycleCount(1);
        initalTimeline.play();

    }

    public void stop_initTimeline(Monkey monkey, Stick stick, Towers towers, Banana banana){
        initalTimeline.stop();
        stick.StickGrowTimeline(monkey,stick,towers,banana);

    }

    //After the monkey crosses this function moves everything back to starting position until stop scene is called
    public void moveScene( Monkey monkey, Stick stick, Towers towers, Banana banana) {
        Rectangle first = monkey.getCurrentTower();

        Rectangle second = monkey.getNextTower();

        double start_pos = 20;

        moveSceneTimeline = new Timeline(new KeyFrame(Duration.seconds(0.009), event -> {
            if (monkey.getMonkeyImageView().getX() > start_pos) {
                first.setX(first.getX() - 2);
                second.setX(second.getX() - 2);
                monkey.getMonkeyImageView().setX(monkey.getMonkeyImageView().getX() - 2);
                stick.getStick().setY(stick.getStick().getY()+2);
            } else {
                stopping_scene(monkey,stick,towers, banana);



                Rotate rotate=new Rotate();
                stick.getStick().setHeight(5);
                rotate.setAngle(270);
                rotate.setPivotX(stick.getStick().getX());
                rotate.setPivotY(465);
                stick.getStick().getTransforms().add(rotate);
                stick.getStick().setX(first.getWidth()+first.getX()-stick.getStick().getWidth());
                stick.getStick().setY(first.getY()-stick.getStick().getHeight());



            }
        }));

        moveSceneTimeline.setCycleCount(Timeline.INDEFINITE);
        moveSceneTimeline.play();
    }

    //IF condition is fullfilled this stops the scene from moving more
    public void stopping_scene(Monkey monkey, Stick stick, Towers towers, Banana banana)
    {
        if (moveSceneTimeline != null) {
            moveSceneTimeline.stop();
            initTimeline(monkey,stick, towers, banana);
        }
    }
}
