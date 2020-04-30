package com.mewtwo2.settlethescore;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import com.mewtwo2.settlethescore.activities.CoinFlipActivity;
import com.mewtwo2.settlethescore.activities.GameActivity;
import com.mewtwo2.settlethescore.activities.MainActivity;
import com.mewtwo2.settlethescore.activities.PickGameActivity;
import com.mewtwo2.settlethescore.activities.PopUpActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.activities.RouletteActivity;
import com.mewtwo2.settlethescore.activities.TicTacToeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TicTacToeActivityTest {

    @Rule
    public IntentsTestRule<TicTacToeActivity> mActivityRule = new IntentsTestRule<>(
            TicTacToeActivity.class);

    @Test
    public void PlayerOneWinsTest() {
        onView(withId(R.id.button_00)).perform(click());
        onView(withId(R.id.button_10)).perform(click());
        onView(withId(R.id.button_01)).perform(click());
        onView(withId(R.id.button_20)).perform(click());
        onView(withId(R.id.button_02)).perform(click());

        onView(withText(R.string.player_one_wins)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void PlayerTwoWinsTest() {
        onView(withId(R.id.button_11)).perform(click());
        onView(withId(R.id.button_00)).perform(click());
        onView(withId(R.id.button_01)).perform(click());
        onView(withId(R.id.button_10)).perform(click());
        onView(withId(R.id.button_12)).perform(click());
        onView(withId(R.id.button_20)).perform(click());

        onView(withText(R.string.player_two_wins)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}