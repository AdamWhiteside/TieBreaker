package com.mewtwo2.settlethescore;

import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.matcher.UriMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.mewtwo2.settlethescore.activities.MainActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.hasBackground;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class GameLaunchingTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void LaunchEachGameTest() {
        List<GameInfo> gameInfoList = GameRegistry.getGameInfoList();
        for (int i = 0; i < gameInfoList.size(); i++) {
            GameInfo gameInfo = gameInfoList.get(i);

            //Open the PickGameMenu
            onView(withId(R.id.btnPickGame)).perform(click());
            onView(withId(R.id.game_view)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            //Check that text views have been updated correctly
            onView(withId(R.id.textGameName)).check(matches(withText(gameInfo.nameID)));

            if(gameInfo.gameType == GameInfo.GameType.Interactive)
                onView(withId(R.id.textPlayerNumber)).check(matches(withText(R.string.player_interactive)));
            else
                onView(withId(R.id.textPlayerNumber)).check(matches(withText(R.string.player_one)));

            onView(withId(R.id.textGameInstructions)).check(matches(withText(gameInfo.instructionsID)));

            //Check that the correct game launches.
            onView(withId(R.id.buttonReady)).perform(click());
            intended(hasComponent(gameInfo.activity.getName()));
            pressBack();
        }
    }
}
