package com.mewtwo2.settlethescore;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mewtwo2.settlethescore.activities.CoinFlipActivity;
import com.mewtwo2.settlethescore.activities.GameActivity;
import com.mewtwo2.settlethescore.activities.MainActivity;
import com.mewtwo2.settlethescore.activities.PickGameActivity;
import com.mewtwo2.settlethescore.activities.PopUpActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.activities.RouletteActivity;
import com.mewtwo2.settlethescore.activities.TicTacToeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PickGameActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void SettleTheScoreBtnWorks() {
        onView(withId(R.id.btnSettleTheScore)).perform(click());
        intended(hasComponent(PopUpActivity.class.getName()));
    }

    @Test
    public void PickGameBtnWorks() {
        onView(withId(R.id.btnPickGame)).perform(click());
        intended(hasComponent(PickGameActivity.class.getName()));
    }
}
