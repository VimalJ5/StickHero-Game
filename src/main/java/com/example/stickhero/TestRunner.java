package com.example.stickhero;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class TestRunner {
    public static void main(String[] args){
        Result result=JUnitCore.runClasses(TowersFailTest.class, TowersPassTest.class,TowerTest.class,MonkeyTest.class);
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}

