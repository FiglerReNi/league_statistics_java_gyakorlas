package com.codecool.leaguestatistics.model;

public class Attacker extends Player{

    private final double skillRateMultiplier = 1.2;
    private final double defAbilityMultiplier = 0.5;

    public Attacker() {
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
