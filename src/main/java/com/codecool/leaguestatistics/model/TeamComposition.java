package com.codecool.leaguestatistics.model;

public enum TeamComposition {

    ATTACKER(2),
    MIDFIELDER(4),
    DEFENDER(4),
    GOALKEEPER(1);

    private final int value;

    TeamComposition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
