package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.Player;
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
    private int scoredGoalsTeam1;
    private int scoredGoalsTeam2;
    private int sumSkillRate;
    private int sumDefAbility;

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
        int roundNumber = Utils.getRandomValue(3, 6);
        scoredGoalsTeam1 = 0;
        scoredGoalsTeam2 = 0;
        for (int i = 0; i < roundNumber; i++) {
            sumSkillRate = 0;
            sumDefAbility = 0;
            int choice = Utils.getRandomValue(0, 2);
            if (choice == 1) {
                playRound(team1, team2, "team1");
            } else {
                playRound(team2, team1, "team2");
            }
        }
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

    private int getSumSkillRate(Team attacker) {
        return attacker.getPlayers().stream()
                .mapToInt(Player::getSkillRate)
                .sum();
    }

    private int getSumDefAbility(Team defender) {
        return defender.getPlayers().stream()
                .mapToInt(Player::getDefAbility)
                .sum();
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

    private void playRound(Team attacker, Team defender, String team){
        sumSkillRate = getSumSkillRate(attacker);
        sumDefAbility = getSumDefAbility(defender);
        if(sumSkillRate > sumDefAbility) {
            switch(team){
                case "team1":
                    scoredGoalsTeam1 += getScoredGoals(attacker);
                    break;
                case "team2":
                    scoredGoalsTeam2 += getScoredGoals(attacker);
                    break;
            }
        }
        setSumDefAbilityAfterRound(attacker);
        setSumDefAbilityAfterRound(defender);
    }

    private void setSumDefAbilityAfterRound(Team team){
        team.getPlayers()
                .forEach(p-> p.setDefAbility(p.getDefAbility()-1));

    }
}
