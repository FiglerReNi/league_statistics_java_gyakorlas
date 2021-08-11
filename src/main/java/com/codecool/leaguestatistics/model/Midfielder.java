package com.codecool.leaguestatistics.model;

public class Midfielder extends Player{


    private final double skillRateMultiplier = 1.1;
    private final double defAbilityMultiplier = 1.1;

    public Midfielder() {
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
