package com.example.walmart

// Actions and assertions on Country Details screen

fun countries(func: CountryDetailsRobot.() -> Unit) =
    CountryDetailsRobot().apply{ func() }

class CountryDetailsRobot : BaseTestRobot() {

    fun tapBackButtonOnCountryDetailsButton() {
        pressBack()
    }

    fun assertThat(): Assert {
        return Assert()
    }

    open inner class Assert {
        fun verifyCountryIsDisplayed(string: String) {
            matcherIsDisplayed(com.example.walmart.presentation.R.id.name_with_region_view, string)
        }

    }
}