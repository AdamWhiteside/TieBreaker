package com.mewtwo2.settlethescore;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mewtwo2.settlethescore.activities.CoinFlipActivity;
import com.mewtwo2.settlethescore.activities.PopUpActivity;
import com.mewtwo2.settlethescore.activities.R;
import com.mewtwo2.settlethescore.activities.RouletteActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

import java.util.*;

@RunWith(AndroidJUnit4.class)
public class CoinFlipActivityTest {


    @Rule
    public ActivityTestRule<CoinFlipActivity> cfActivityRule = new ActivityTestRule<>(
            CoinFlipActivity.class);

    @Test
    public void HeadsBtnWorks() {
        //Matcher hello = withId(R.id.heads_btn);

        onView(withId(R.id.heads_btn)).perform(click());

        intended(hasComponent(PopUpActivity.class.getName()));

        //onView(hello).check(true);
    }

    @Test
    public void TailsBtnWorks() {
        onView(withId(R.id.tails_btn)).perform(click());
    }
}
