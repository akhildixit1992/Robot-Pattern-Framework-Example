package com.example.walmart

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/*
    Common reusable matchers that the robots can use during actions and assertions
 */
open class BaseTestRobot {
    fun enterTextOnViewWithId(@IdRes viewId: Int, text: String) {
            onView(withId(viewId)).perform(ViewActions.typeText(text))
            closeSoftKeyboard()
    }

    fun clickViewWithId(@IdRes viewId: Int) {
            onView(withId(viewId))
                .check(matches(isDisplayed()))
                .perform(click())
    }

    fun viewWithIdIsDisplayed(@IdRes viewId: Int) {
            onView(withId(viewId))
                .check(matches(isDisplayed()))
    }

    fun textIsDisplayed(text: String) {
            onView(withText(text))
    }

    fun matcherIsDisplayed(@IdRes viewId: Int, string: String) {
        onView(
            allOf(
                withId(viewId), withText(string),isDisplayed())
        )
    }

    fun clickOnRecyclerListItem(@IdRes recyclerViewId: Int, childId: Int, position: Int){
        onView(allOf(withId(recyclerViewId), childAtPosition(
                    withId(childId), position))).
        perform(actionOnItemAtPosition<ViewHolder>(position, click()))
    }

    fun pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    // Customized matcher to click on list item at a specific position on the recycler view
    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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