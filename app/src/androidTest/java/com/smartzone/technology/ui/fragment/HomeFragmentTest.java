package com.smartzone.technology.ui.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.smartzone.technology.R;
import com.smartzone.technology.EspressoIdlingResouce;
import com.smartzone.technology.ui.HomeActvityTest;
import com.smartzone.technology.ui.fragment.home.HomeFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {
    @Rule
    //Activity Test Rule for the activity which contains my login fragment
    public ActivityTestRule<HomeActvityTest> mActivityRule = new ActivityTestRule(HomeActvityTest.class);


    @Before
    public void init() {
        //Initialised my login fragment
        Fragment fragment = new HomeFragment();
        //Used the helper method provided by TestFragmentActivity to load my login fragment
        mActivityRule.getActivity().setFragment(fragment);
        IdlingRegistry.getInstance().register(EspressoIdlingResouce.getIdlingResource());
    }
    @Test
    public void testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.emptyRecycle))
                .inRoot(RootMatchers
                        .withDecorView(
                                Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.emptyRecycle))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.emptyRecycle);
        int itemCount = recyclerView.getAdapter().getItemCount();
        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.emptyRecycle))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResouce.getIdlingResource());
    }

    @Test
    public void testCaseForRecyclerItemView() {

        Espresso.onView(ViewMatchers.withId(R.id.emptyRecycle))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(withViewAtPosition(1, Matchers.allOf(
                        ViewMatchers.withId(R.id.textTitel), isDisplayed()))));
    }

    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}
