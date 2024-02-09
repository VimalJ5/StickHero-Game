package com.example.stickhero;

import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TowersPassTest {
    @Test
    public void createTower() {
        Rectangle tower=new Rectangle();
        tower.setHeight(370);
        tower.setWidth(0);
        tower.setX(0);
        tower.setY(470);
        assertEquals((int)tower.getHeight(),370);
        assertEquals((int)tower.getHeight(),470);
    }
}
