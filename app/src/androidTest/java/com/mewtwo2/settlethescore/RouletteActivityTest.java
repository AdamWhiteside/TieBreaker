package com.mewtwo2.settlethescore;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mewtwo2.settlethescore.activities.CoinFlipActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.activities.RouletteActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RouletteActivityTest {


    @Rule
    public ActivityTestRule<RouletteActivity> rActivityRule = new ActivityTestRule<>(
            RouletteActivity.class);

    @Test
    public void RouletteBtnWorks() {
        onView(withId(R.id.spinBtn)).perform(click()); //spin button works
    }
}
