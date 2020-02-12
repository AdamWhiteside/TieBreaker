package com.mewtwo2.settlethescore;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles all registration for games.
 */
public class GameRegistry {

    private static GameRegistry _instance = null;
    private Set<GameInfo> gameSet;

    private GameRegistry() {
        gameSet = new HashSet<>();
    }

    public static void RegisterGame(Class<?> activity, int instructionsID, int logoID )
    {
        getInstance().gameSet.add(new GameInfo(activity,instructionsID,logoID));
    }


    private static GameRegistry getInstance()
    {
        if(_instance == null)
        {
            _instance = new GameRegistry();
        }
        return _instance;
    }
}
