package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.Team;
import com.codecool.leaguestatistics.view.Display;

import java.util.*;

import static com.codecool.leaguestatistics.Utils.getRandomValue;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> league;
    private final Display display;

    public Season() {
        league = new ArrayList<>();
        this.display = new Display();
    }

    /**
     * Fills league with new teams and simulates all games in season.
     * After all games played calls table to be displayed.
     */
    public void run() {
        this.league = LeagueFactory.createLeague(2);
        this.display.createHeaderForSingleMatchTable();
        playAllGames();
        this.display.createStrongestDivision(this.league);
        this.display.createAllTeamsStatisticTable(this.league);
        this.display.createTopTeamsWithLeastLosesTable(this.league);
        this.display.createTeamsWithPlayersWithoutGoalsTable(this.league);
        this.display.createMostTalentedPlayerInDivisionTable(this.league);
        this.display.createTopPlayersFromEachTeamTable(this.league);
        this.display.createPlayersWithAtLeastXGoalsTable(this.league);

        // Call Display methods below
    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        for (int i = 0; i < league.size(); i++) {
            for (int j = i + 1; j < league.size(); j++) {
                playMatch(league.get(i), league.get(j));
            }
        }
    }

    /**
     * Plays single game between two teams and displays result after.
     */
    private void playMatch(Team team1, Team team2) {
        int scoredGoalsTeam1 = getScoredGoals(team1);
        int scoredGoalsTeam2 = getScoredGoals(team2);
        if (scoredGoalsTeam1 > scoredGoalsTeam2) {
            team1.setWins(team1.getWins() + 1);
            team2.setLoses(team2.getLoses() + 1);
        } else if (scoredGoalsTeam1 < scoredGoalsTeam2) {
            team2.setWins(team2.getWins() + 1);
            team1.setLoses(team1.getLoses() + 1);
        } else {
            team1.setDraws(team1.getDraws() + 1);
            team2.setDraws(team2.getDraws() + 1);
        }
        display.createSingleMatchResultTable(Arrays.asList(team1.getName(), String.valueOf(scoredGoalsTeam1), String.valueOf(scoredGoalsTeam2), team2.getName()));

    }

    /**
     * Checks for each player of given team chance to score based on skillrate.
     * Adds scored goals to player's and team's statistics.
     * @return All goals scored by the team in current game
     */
    private int getScoredGoals(Team team) {
        return (int) team.getPlayers().stream()
                .filter(p -> p.getSkillRate() >= getRandomValue(1, 101))
                .peek(p -> p.setGoals(p.getGoals() + 1))
                .count();
    }
}
