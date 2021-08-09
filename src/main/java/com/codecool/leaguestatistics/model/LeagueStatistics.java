package com.codecool.leaguestatistics.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides all necessary statistics of played season.
 */
public class LeagueStatistics {

    /**
     * Gets all teams with highest points order, if points are equal next deciding parameter is sum of goals of the team.
     */
    public static List<Team> getAllTeamsSorted(List<Team> teams) {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getCurrentPoints).thenComparing(
                        t -> t.getPlayers().stream().map(Player::getGoals).reduce(0, Integer::sum)
                        ).reversed()
                ).collect(Collectors.toList());
    }

    /**
     * Gets all players from each team in one collection.
     */
    public static List<Player> getAllPlayers(List<Team> teams) {
        return teams.stream()
                .map(Team::getPlayers)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Gets team with the longest name
     */
    public static Team getTeamWithTheLongestName(List<Team> teams) {
        return teams.stream()
                .max(Comparator.comparing(t -> t.getName().length()))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Gets top teams with least number of lost matches.
     * If the amount of lost matches is equal, next deciding parameter is team's current points value.
     *
     * @param teamsNumber The number of Teams to select.
     * @return Collection of selected Teams.
     */
    public static List<Team> getTopTeamsWithLeastLoses(List<Team> teams, int teamsNumber) {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getLoses).reversed().thenComparing(Team::getCurrentPoints).reversed())
                .limit(teamsNumber)
                .collect(Collectors.toList());
    }

    /**
     * Gets a player with the biggest goals number from each team.
     */
    public static List<Player> getTopPlayersFromEachTeam(List<Team> teams) {
        return teams.stream()
                .map(Team::getBestPlayer)
                .collect(Collectors.toList());
    }

    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams) {
        return teams.stream()
                .filter(
                        t -> t.getPlayers().stream()
                                .anyMatch(p -> p.getGoals() == 0)
                ).collect(Collectors.toList());
    }

    /**
     * Gets players with given or higher number of goals scored.
     *
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        return getAllPlayers(teams).stream()
                .filter(p -> p.getGoals() >= goals)
                .collect(Collectors.toList());
    }

    /**
     * Gets the player with the highest skill rate for given Division.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams, Division division) {
        return teams.stream()
                .filter(t -> t.getDivision() == division)
                .map(Team::getPlayers)
                .flatMap(Collection::stream)
                .max(Comparator.comparing(Player::getSkillRate))
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * OPTIONAL
     * Returns the division with greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        return teams.stream()
                .collect(Collectors.groupingBy(Team::getDivision))
                .entrySet().stream()
                .sorted((Map.Entry.comparingByValue(Comparator.comparing(
                        (List<Team> e) -> e.stream().map(Team::getCurrentPoints).reduce(0, Integer::sum)).thenComparing(
                        (List<Team> e) -> e.stream().map(Team::getWins).reduce(0, Integer::sum)
                ).reversed())))
                .limit(1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()).get(0);
    }
}
