package com.codecool.leaguestatistics;


import java.lang.reflect.InvocationTargetException;
import com.codecool.leaguestatistics.controller.Season;

public class Program {

    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Season season = new Season();
        season.run();

//        Példa: String alapján new Object
//        String className = "Attacker";
//        Object test = Class.forName("com.codecool.leaguestatistics.model." + className).getDeclaredConstructor().newInstance();
//        System.out.println(test);

    }
}
