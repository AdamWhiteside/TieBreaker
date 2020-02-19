package com.mewtwo2.settlethescore.registration;

import java.io.Serializable;

public class GameInfo implements Serializable {
    public enum GameType {Interactive, Noninteractive}

    public Class<?> activity;
    public int nameID;
    public int instructionsID;
    public int logoID;
    public GameType gameType;

    public GameInfo(Class<?> activity, int nameID, int instructionsID, int logoID, GameType gameType)
    {
        this.activity = activity;
        this.nameID = nameID;
        this.instructionsID = instructionsID;
        this.logoID = logoID;
        this.gameType = gameType;
    }
}
