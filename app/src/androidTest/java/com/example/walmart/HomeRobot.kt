package com.example.walmart

// Actions and assertions on the country screen (home)

fun home(func: HomeRobot.() -> Unit) =
    HomeRobot().apply{ func() }

class HomeRobot: BaseTestRobot() {

    fun tapCountrySearchBar() {
        clickViewWithId(com.example.walmart.presentation.R.id.action_search)
    }

    fun typeCountryNameInSearchBar(string: String) {
        enterTextOnViewWithId(com.google.android.material.R.id.search_src_text, string)
    }

    fun tapCountryOnOthPosition(){
        clickOnRecyclerListItem(com.example.walmart.presentation.R.id.country_recycler_view,
        com.example.walmart.presentation.R.id.swipe_refresh_layout, 0)
    }

    fun assertThat(): Assert {
        return Assert()
    }

    open inner class Assert {
        fun titleIsShown(){
            viewWithIdIsDisplayed(androidx.constraintlayout.widget.R.id.action_bar)
        }

        fun titleTextIsShown() {
            textIsDisplayed("Countries List")
        }

    }

}