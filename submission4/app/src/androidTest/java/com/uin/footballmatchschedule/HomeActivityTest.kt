package com.uin.footballmatchschedule

import android.os.Handler
import android.support.test.espresso.PerformException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.util.HumanReadables
import android.support.test.espresso.util.TreeIterables
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.uin.footballmatchschedule.R.id.*
import kotlinx.coroutines.experimental.timeunit.TimeUnit
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matchers.not
import android.widget.Toast
import android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.uin.footballmatchschedule.util.Run
import android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import android.os.Looper
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.swipeDown


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(3000)
        onView(withId(rec))
                .check(matches(isDisplayed()))
        onView(withId(rec)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        Thread.sleep(1000)
        onView(withId(rec)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)

        pressBack()

        Thread.sleep(1000)

        onView(withId(navigation))
                .check(matches(isDisplayed()))
        onView(withId(navigation_fav)).perform(click())

        Thread.sleep(1000)

        onView(withId(navigation))
                .check(matches(isDisplayed()))
        onView(withId(navigation_next)).perform(click())

        Thread.sleep(3000)

        onView(withId(rec))
                .check(matches(isDisplayed()))
        onView(withId(rec)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(rec)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        Thread.sleep(1000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)

        pressBack()

        Thread.sleep(1000)

        onView(withId(navigation))
                .check(matches(isDisplayed()))
        onView(withId(navigation_fav)).perform(click())

        Thread.sleep(1000)

        onView(withId(rec))
                .check(matches(isDisplayed()))
        onView(withId(rec)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rec)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(1000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)

        pressBack()

        Thread.sleep(1000)

        onView(withId(swipeRefresh)).perform(swipeDown())

        Thread.sleep(1000)

        onView(withId(rec))
                .check(matches(isDisplayed()))
        onView(withId(rec)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rec)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(1000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)

        pressBack()

        Thread.sleep(1000)

        onView(withId(swipeRefresh)).perform(swipeDown())

        Thread.sleep(1000)

    }
}