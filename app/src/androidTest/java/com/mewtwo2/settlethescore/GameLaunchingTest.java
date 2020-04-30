package com.mewtwo2.settlethescore;

import android.content.ComponentName;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.mewtwo2.settlethescore.activities.CoinFlipActivity;
import com.mewtwo2.settlethescore.activities.GameActivity;
import com.mewtwo2.settlethescore.activities.MainActivity;
import com.mewtwo2.settlethescore.activities.PickGameActivity;
import com.mewtwo2.settlethescore.activities.PopUpActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.activities.RouletteActivity;
import com.mewtwo2.settlethescore.activities.TicTacToeActivity;
import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

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

            //Check that the popup activity has the correct text
            onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(matches(withText(gameInfo.nameID)));
            onView(withId(R.id.instruction_text)).check(matches(withText(gameInfo.instructionsID)));

            //Check that the correct game launches.
            onView(withId(R.id.button_ready)).perform(click());
            intended(hasComponent(gameInfo.activity.getName()));
            pressBack();
        }
    }
}
