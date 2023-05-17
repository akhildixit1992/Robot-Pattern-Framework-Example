package com.example.walmart

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.walmart.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDetailsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /*
    This test will test country details screen by tapping the first element
    on home screen and assert if we show the country we clicked
    */
    @Test
    fun testCountryDetails() {
        home {
            tapCountryOnOthPosition()
        }
        countries {
            assertThat().verifyCountryIsDisplayed("Afghanistan, AS")
            tapBackButtonOnCountryDetailsButton()
        }

        home {
            assertThat().titleIsShown()
        }
    }
}