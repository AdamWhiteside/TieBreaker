package com.mewtwo2.settlethescore;

public class GameInfo {
    public Class<?> activity;
    public int instructionsID;
    public int logoID;

    public GameInfo(Class<?> activity, int instructionsID, int logoID)
    {
        this.activity = activity;
        this.instructionsID = instructionsID;
        this.logoID = logoID;
    }
}
