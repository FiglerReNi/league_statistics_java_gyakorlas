package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Provides full set of teams with players
 */
public class LeagueFactory {

    /**
     * For each division, creates given amount of teams. Each team gets a newly created collection of players.
     * The amount of players should be taken from Utils.TEAM_SIZE
     * @param teamsInDivision Indicates number of teams are in division
     * @return Full set of teams with players
     */
    public static List<Team> createLeague(int teamsInDivision) {
        List<Team> teams = new ArrayList<>();
        IntStream.range(0, teamsInDivision)
                .forEach(i -> {
                    Stream.of(Division.values()).forEach(
                            d -> teams.add(new Team(d, getPlayers()))
                    );
                });
        return teams;
    }

    /**
     * Returns a collection with a given amount of newly created players
     */
    public static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < TeamComposition.GOALKEEPER.getValue(); i++) {
            players.add(new Goalkeeper());
        }
        for (int i = 0; i < TeamComposition.DEFENDER.getValue(); i++) {
            players.add(new Defender());
        }
        for (int i = 0; i < TeamComposition.MIDFIELDER.getValue(); i++) {
            players.add(new Midfielder());
        }
        for (int i = 0; i < TeamComposition.ATTACKER.getValue(); i++) {
            players.add(new Attacker());
        }
        return players;
    }

}
