package com.example.stickhero;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TowerTest {
    @Test
    public void createTower(){
        assertEquals(Towers.count,2);
        assertEquals(Towers.count,1);

    }
}
