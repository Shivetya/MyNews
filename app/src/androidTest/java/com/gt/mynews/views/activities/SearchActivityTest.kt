package com.gt.mynews.views.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class SearchActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchActivityTest() {
        val appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.activity_toolbar),
                                        childAtPosition(
                                                withClassName(`is`("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.activity_main_navigation_view),
                                        0)),
                        4),
                        isDisplayed()))
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.edittext_keyword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textinputlayout_keyword),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("cat"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.edittext_keyword), withText("cat"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textinputlayout_keyword),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText2.perform(pressImeActionButton())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.fragment_search_edittext_begin_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_search_textinputlayout_begin_date),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText3.perform(click())

        val datePicker = onView(
                allOf(IsInstanceOf.instanceOf(android.widget.DatePicker::class.java),
                        childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        datePicker.check(matches(isDisplayed())).perform(pressBack())

        val appCompatEditText4 = onView(
                allOf(withId(R.id.fragment_search_edittext_end_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_search_textinputlayout_end_date),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText4.perform(click())

        val datePicker2 = onView(
                allOf(IsInstanceOf.instanceOf(android.widget.DatePicker::class.java),
                        childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        datePicker2.check(matches(isDisplayed())).perform(pressBack())

        closeSoftKeyboard()
        Thread.sleep(2000)

        val materialButton3 = onView(
                allOf(withId(R.id.fragment_search_button_search),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_search_framelayout),
                                        0),
                                3),
                        isDisplayed()))
        materialButton3.perform(click())

        val frameLayout = onView(
                allOf(withId(R.id.activity_search_framelayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        frameLayout.check(matches(isDisplayed()))
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
