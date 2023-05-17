package com.example.walmart

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.walmart.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    /*
        Verifies the home screen was displayed
     */
    @Test
    fun testHomeScreen() {
        home {
            assertThat().titleIsShown()
            assertThat().titleTextIsShown()
        }
    }

    /*
        Verifies tapping search button and searching for a given country
     */
    @Test
    fun testSearchCountry() {
        home {
            tapCountrySearchBar()
            typeCountryNameInSearchBar("United States Of America")
        }
        countries {
            assertThat().verifyCountryIsDisplayed("United States of America, NA")
        }
    }
}