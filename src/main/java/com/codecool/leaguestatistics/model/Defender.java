package com.codecool.leaguestatistics.model;

public class Defender extends Player{

    private final double skillRateMultiplier = 0.5;
    private final double defAbilityMultiplier = 1.4;

    public Defender() {
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
