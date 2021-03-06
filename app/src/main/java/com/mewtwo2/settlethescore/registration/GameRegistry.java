package com.mewtwo2.settlethescore.registration;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * Handles all registration for games.
 */
public class GameRegistry {

    private static GameRegistry _instance = null;
    private Dictionary<Class<?>,GameInfo> gameDictionary;
    private List<GameInfo> gameInfoList;

    private GameRegistry() {
        gameDictionary = new Hashtable<>();
        gameInfoList = new ArrayList<>();
    }

    public static void RegisterGame(Class<?> activity, int nameID, int instructionsID, int logoID, GameInfo.GameType gameType)
    {
        GameRegistry instance = getInstance();
        GameInfo gi = new GameInfo(activity,nameID,instructionsID,logoID,gameType);

        instance.gameInfoList.add(gi);
        instance.gameDictionary.put(activity, gi);
    }

    public static GameInfo getGameInfo(Class<?> activityClass)
    {
        return getInstance().gameDictionary.get(activityClass);
    }

    public static GameInfo getRandomGameInfo()
    {
        GameRegistry instance = getInstance();

        return instance.gameInfoList.get(new Random().nextInt(instance.gameInfoList.size()));
    }

    public static List<GameInfo> getGameInfoList()
    {
        return getInstance().gameInfoList;
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
