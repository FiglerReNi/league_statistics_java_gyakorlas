package com.codecool.leaguestatistics.model;

public class Goalkeeper extends Player{

    private final double skillRateMultiplier = 0;
    private final double defAbilityMultiplier = 1.5;

    public Goalkeeper() {
        super();
    }

    @Override
    protected double getSkillRateMultiplier() {
        return skillRateMultiplier;
    }

    @Override
    protected double getDefAbilityMultiplier() {
        return defAbilityMultiplier;
    }
}
