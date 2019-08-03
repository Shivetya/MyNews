package com.gt.mynews.views.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gt.mynews.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NotificationActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun notificationActivityTest() {
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
                        7),
                        isDisplayed()))
        navigationMenuItemView.perform(click())

        val materialCheckBox = onView(
                allOf(withId(R.id.fragment_search_checkbox_business_2), withText("Business"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linear_checkboxes_root),
                                        0),
                                1),
                        isDisplayed()))
        materialCheckBox.perform(click())

        val materialCheckBox2 = onView(
                allOf(withId(R.id.fragment_search_checkbox_entrepreneurs_3), withText("Entrepreneurs"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linear_checkboxes_root),
                                        1),
                                0),
                        isDisplayed()))
        materialCheckBox2.perform(click())

        val materialCheckBox3 = onView(
                allOf(withId(R.id.fragment_search_checkbox_sports_5), withText("Sports"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linear_checkboxes_root),
                                        2),
                                0),
                        isDisplayed()))
        materialCheckBox3.perform(click())

        val switchMaterial = onView(
                allOf(withId(R.id.activity_notifications_switch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()))
        switchMaterial.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.edittext_keyword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textinputlayout_keyword),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("bonjour"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.edittext_keyword), withText("bonjour"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textinputlayout_keyword),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText2.perform(pressImeActionButton())

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

        val actionMenuItemView = onView(
                allOf(withId(R.id.menu_activity_main_params), withContentDescription("Parameters"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_toolbar),
                                        2),
                                1),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        val materialTextView = onView(
                allOf(withId(R.id.title), withText("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        materialTextView.perform(click())

        val editText = onView(
                allOf(withId(R.id.edittext_keyword), withText("bonjour"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textinputlayout_keyword),
                                        0),
                                0),
                        isDisplayed()))
        editText.check(matches(withText("bonjour")))


        materialCheckBox3.check(matches(isChecked()))
        switchMaterial.check(matches(isChecked()))

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
