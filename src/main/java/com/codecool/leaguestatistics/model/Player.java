package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.NamesGenerator;

/**
 * Represents player
 */
public abstract class Player {

    /**
     * Player's name
     */
    private String name;

    /**
     * SkillRate is a percentage chance to score a goal
     */
    private int skillRate;

    private int defAbility;

    /**
     * Amount of scored goals
     */
    private int goals;

    public Player() {
        name = NamesGenerator.getPlayerName();
        this.skillRate = this.creaateSkillRate();
        this.defAbility = this.createDefAbility();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillRate() {
        return skillRate;
    }

    public void setSkillRate(int skillRate) {
        this.skillRate = skillRate;
    }

    public int getDefAbility() {
        return defAbility;
    }

    public void setDefAbility(int defAbility) {
        this.defAbility = defAbility;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    protected abstract double getSkillRateMultiplier();
    protected abstract double getDefAbilityMultiplier();

    private int creaateSkillRate(){
        return (int)(getPlayerSkillRate() * getSkillRateMultiplier());
    }

    private int createDefAbility(){
        return (int)(getPlayerDefAbility() * getDefAbilityMultiplier());
    }

    private static int getPlayerSkillRate() {
        return Utils.getRandomValue(5, 21);
    }

    private static int getPlayerDefAbility() {
        return Utils.getRandomValue(1, 30);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", skillRate=" + skillRate +
                ", defAbility=" + defAbility +
                ", goals=" + goals +
                '}';
    }
}
