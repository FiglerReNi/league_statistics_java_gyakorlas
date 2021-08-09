package com.codecool.leaguestatistics.service;

import com.codecool.leaguestatistics.model.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayService {

    public static List<Player> sortedPlayerReverseOrder(List<Player> players){
        return players.stream()
                .sorted(Comparator.comparing(Player::getGoals).reversed())
                .collect(Collectors.toList());
    }
}
