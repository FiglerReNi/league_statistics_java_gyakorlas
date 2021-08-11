package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.LeagueStatistics;
import com.codecool.leaguestatistics.model.Player;
import com.codecool.leaguestatistics.model.Team;
import com.codecool.leaguestatistics.service.DisplayService;

import java.util.List;
import java.util.stream.Stream;

/**
 * Provides console view
 */
public class Display {

    public void createSingleMatchResultTable(List<String> data){
            createRowDivider(105);
            System.out.printf("%5s%-44s%1s%1s%1s%44s\n"," ", data.get(0),data.get(1)," : ",data.get(2),data.get(3));
    }

    public void createHeaderForSingleMatchTable(){
            System.out.printf("%5s%-40s%6s%2s%5s%40s\n"," ","Team 1","Goals ",": ","Goals","Team 2");
    }

    public void createAllTeamsStatisticTable(List<Team> teams){
        List<Team> teamsForStat = LeagueStatistics.getAllTeamsSorted(teams);
        createTeamsTable(teamsForStat, "All Teams Sorted");
    }

    public void createTopTeamsWithLeastLosesTable(List<Team> teams){
        List<Team> teamsForStat = LeagueStatistics.getTopTeamsWithLeastLoses(teams, Utils.TEAMS_STATISTIC_LIMIT);
        createTeamsTable(teamsForStat, "Top Three Teams With Least Loses");
    }

    public void createTeamsWithPlayersWithoutGoalsTable(List<Team> teams){
        List<Team> teamsForStat = LeagueStatistics.getTeamsWithPlayersWithoutGoals(teams);
        createTeamsTable(teamsForStat, "Teams With Players Without Goals");
    }

    private void createTeamsTable(List<Team> teamsForStat, String header){
        createHeader(header);
        System.out.printf("%5s%-"+getLongestNameLength(teamsForStat)+"s%20s%20s%20s%20s%20s\n"," ","Name","Points","Goals","Wins","Draws","Loses");
        createRowDivider(200);
        teamsForStat.forEach(
                t->System.out.printf("%5s%-"+getLongestNameLength(teamsForStat)+"s%20s%20s%20s%20s%20s\n"," ",t.getName(),t.getCurrentPoints(),t.getPlayers().stream().map(Player::getGoals).reduce(0, Integer::sum),t.getWins(),t.getDraws(),t.getLoses())
        );
    }

    public void createTopPlayersFromEachTeamTable(List<Team> teams){
        List<Player> playerForStat = DisplayService.sortedPlayerReverseOrder(LeagueStatistics.getTopPlayersFromEachTeam(teams));
        createPlayersTable(playerForStat, "Top Players From Each Team");
    }

    public void createPlayersWithAtLeastXGoalsTable(List<Team> teams){
        List<Player> playerForStat = DisplayService.sortedPlayerReverseOrder(LeagueStatistics.getPlayersWithAtLeastXGoals(teams, Utils.GOALS_STATISTIC_LIMIT));
        createPlayersTable(playerForStat, "Players With At Least " + Utils.GOALS_STATISTIC_LIMIT + " Goals");
    }

    private void createPlayersTable(List<Player> playersForStat, String header){
        createHeader(header);
        System.out.printf("%5s%-50s%20s%20s\n"," ","Name","Goals","Skill rate");
        createRowDivider(200);
        playersForStat.forEach(
                p->System.out.printf("%5s%-50s%20s%20s\n"," ",p.getName(),p.getGoals(),p.getSkillRate())
        );
    }

    public void createMostTalentedPlayerInDivisionTable(List<Team> teams){
        createHeader("Most Talented Player In Division");
        System.out.printf("%5s%-50s%20s%20s%20s\n"," ","Name","Goals","Skill rate", "Division");
        createRowDivider(200);
        Stream.of(Division.values()).forEach(
                d -> {
                    Player playerForStat = LeagueStatistics.getMostTalentedPlayerInDivision(teams, d);
                    System.out.printf("%5s%-50s%20s%20s%20s\n"," ",playerForStat.getName(), playerForStat.getGoals(), playerForStat.getSkillRate(), d.name());
                }
        );
    }

    public void createStrongestDivision(List<Team> teams){
        createHeader("Strongest Division - " + LeagueStatistics.getStrongestDivision(teams).name());
    }

    private void createRowDivider(int rowLength){
        for(int i=0; i<rowLength; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    private int getLongestNameLength(List<Team>teams){
        return LeagueStatistics.getTeamWithTheLongestName(teams).getName().length() +5;
    }

    private void createHeader(String header){
        System.out.println("\n");
        createRowDivider(50);
        System.out.printf("%5s%-16s\n"," ",header);
        createRowDivider(50);
    }

}
