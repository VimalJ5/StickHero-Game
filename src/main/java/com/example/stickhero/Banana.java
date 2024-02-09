package com.example.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Random;

public class Banana {
    private static int banana_count = 25;
    private ImageView bananaImageView;
    private Random random;
    private int chance;

    private Text textfield;

    public Banana(ImageView bananaImageView, Text textfield)
    {
        this.bananaImageView = bananaImageView;
        this.random = new Random();
        this.textfield = textfield;
    }

    public void spawn_bananas(Monkey monkey, Stick stick, Towers towers) {
        chance = random.nextInt(0,9);
        if(chance<10)
        {
            try {
                double iniit_value = monkey.getCurrentTower().getX() + monkey.getCurrentTower().getWidth() + 20;
                double end_val = monkey.getNextTower().getX() - bananaImageView.getFitWidth() - 20;
                double randomX = random.nextDouble(iniit_value,end_val);
                bananaImageView.setX(randomX);
                bananaImageView.setY(monkey.getCurrentTower().getY() + 10);
                bananaImageView.setVisible(true);
            } catch (IllegalArgumentException e) {
                System.out.println("No banana for you");
            }

        } else {
            System.out.println(chance);
            System.out.println("No Bananana");
        }
    }

    public ImageView getBananaImageView() {
        return bananaImageView;
    }

    public void setBananaImageView(ImageView bananaImageView) {
        this.bananaImageView = bananaImageView;
    }

    public void increasebanana(int i)
    {
        banana_count = banana_count + i;
        textfield.setText(Integer.toString(banana_count));
    }

    public static int getBanana_count() {
        return banana_count;
    }

    public static void setBanana_count(int banana_count) {
        Banana.banana_count = banana_count;
    }
}