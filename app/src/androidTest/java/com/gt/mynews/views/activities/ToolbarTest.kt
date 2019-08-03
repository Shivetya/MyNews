package com.gt.mynews.views.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.gt.mynews.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ToolbarTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun toolbarTest() {
        val actionMenuItemView = onView(
                allOf(withId(R.id.menu_activity_main_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        val frameLayout = onView(
                allOf(withId(R.id.activity_search_framelayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        frameLayout.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.activity_toolbar),
                                        childAtPosition(
                                                withClassName(`is`("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.menu_activity_main_params), withContentDescription("Parameters"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_toolbar),
                                        2),
                                1),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        val materialTextView = onView(
                allOf(withId(R.id.title), withText("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        materialTextView.perform(click())

        val switch_ = onView(
                allOf(withId(R.id.activity_notifications_switch),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        1),
                                1),
                        isDisplayed()))
        switch_.check(matches(isDisplayed()))

        val appCompatImageButton2 = onView(
                allOf(withContentDescription("Revenir en haut de la page"),
                        childAtPosition(
                                allOf(withId(R.id.activity_toolbar),
                                        childAtPosition(
                                                withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        val actionMenuItemView3 = onView(
                allOf(withId(R.id.menu_activity_main_params), withContentDescription("Parameters"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_toolbar),
                                        2),
                                1),
                        isDisplayed()))
        actionMenuItemView3.perform(click())

        val materialTextView2 = onView(
                allOf(withId(R.id.title), withText("Help"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        materialTextView2.perform(click())

        val textView = onView(
                allOf(withId(R.id.activity_help_textview), withText(" Welcome in MyNews App ! \n \n How to use this app ? \n Once the app launched, you can see the lasts articles from New York Times in category Top Stories.\n You can navigate in the four tabs, which are Top Stories, Most Popular, Science ans Technology.\n \n The search icon leads you to the search page : you can write a keyword you want to search. Moreover, you can specify one or multiple filter to your search, and a range of date. One the search launched, articles from New York Times that match with your criterions will appear. \n Caution : if nothing appear, there is no article that match, try to expand your search. The option icon can lead you to the notification page : here you can, as the search page, specify criterions to be notified once per day if new articles match. Thanks for your download ! ;) "),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        textView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
