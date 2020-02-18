package com.mewtwo2.settlethescore.registration;

import java.io.Serializable;

public class GameInfo implements Serializable {
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
