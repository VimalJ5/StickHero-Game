package com.example.stickhero;

import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TowersFailTest {

    @Test
    public void createTower() {
        Rectangle tower=new Rectangle();
        tower.setHeight(0);
        tower.setWidth(0);
        tower.setX(0);
        tower.setY(0);
        assertEquals((int)tower.getHeight(),300);
        assertEquals((int)tower.getHeight(),470);
    }
}