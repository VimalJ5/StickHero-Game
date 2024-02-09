package com.example.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource( "Homepage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Ninja Harambe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }


    public static void main(String[] args) throws IOException {
        Scanner in=null;
        try{
            in=new Scanner(new BufferedReader(new FileReader(("src/main/resources/com/example/stickhero/score.txt"))));
            Monkey.setBest_Score(Integer.parseInt(in.next()));
            Banana.setBanana_count(Integer.parseInt(in.next()));
        }finally{
            if(in!=null){
                in.close();
            }
        }
        launch(args);
    }
}