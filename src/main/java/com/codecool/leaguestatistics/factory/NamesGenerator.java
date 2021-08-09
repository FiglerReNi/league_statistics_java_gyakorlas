package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Provides random names for Players and Teams
 */
public class NamesGenerator {

    public static String getPlayerName() {
        return getRandomStringFromFile("src/main/resources/PlayerNames.txt");
    }

    public static String getTeamName() {
        return getRandomStringFromFile("src/main/resources/CityNames.txt") + " " + getRandomStringFromFile("src/main/resources/TeamNames.txt");
    }

    private static String getRandomStringFromFile(String fileName) {
        String str = "";
        try (Stream<String> stream = Files.lines(Paths.get((fileName)), StandardCharsets.UTF_8)) {
            int lineCount = (int) stream.count();
            int randomNumber = Utils.getRandomValue(1, lineCount);
            str = Files.readAllLines(Paths.get((fileName))).get(randomNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
