package com.example.stickhero;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MonkeyTest {
    @Test
    public void createMonkey(){
        assertEquals(Monkey.singleton_count,1);
        assertEquals(Monkey.singleton_count,2);

    }
}
