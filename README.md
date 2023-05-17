# Framework walk through
The way the framework is constructed is it follows Robot pattern framework along with the power of Kotlin using apply().
I have used factory function pattern in my robots to we can use action chaining in my tests and the combination of setting up the framework like this would 
help the readability of the tests, easily maintainable and have a degree of modularisation in the overall structure. 

There are three components in the framework:
1. Robot - Each Screen has its own Robot
2. Tests - Each test signifies the test run of each functionality
3. BaseTestRobot - Contains all the matcher methods which can be used by all the robots

# What is a robot?

Big question which comes to mind is what is a robot? Simple answer is a robot can be thought of as a class that has all the actions and assertions
that are on a given screen

```kotlin
class HomeRobot: BaseTestRobot() {

    fun tapCountrySearchBar() {
        clickViewWithId(com.example.walmart.presentation.R.id.action_search)
    }

    fun typeCountryNameInSearchBar(string: String) {
        enterTextOnViewWithId(com.google.android.material.R.id.search_src_text, string)
    }
}
```
As you can see, we have two tap functionalities which interact with a given screen when the test is kicked off.
The first method taps on the search bar and then enters the country name needed and then we check for a specific assertion as shown below.

```kotlin
fun assertThat(): Assert {
        return Assert()
    }

    open inner class Assert {
        fun titleIsShown() {
            viewWithIdIsDisplayed(androidx.constraintlayout.widget.R.id.action_bar)
        }

        fun titleTextIsShown() {
            textIsDisplayed("Countries List")
        }
    }
```
# How is the test constructed:

```kotlin
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
```
In the above block, you can see the test is structured by each robot and specific actions which makes tests look readable and concise.

# Benefits of this Design:
1. Code Readability
2. Easy Debugging
3. Scalability as changes can be easily done without disturbing a bunch of code
4. Easy maintenance
5. Modularization


## Steps to run the tests
1. Tests are written in the main app folder under androidTest/java/com.example.walmart package
2. Before you run the tests, make sure animations and transitions are turned off on the emulatoras this is a requirement for instrumentation tests to run
3. Once the above step is done you can run the tests in the app directory using `./gradlew app:connectedAndroidTest`
4. If the above step fails due to some issue, you can individually run the tests by pressing the green button beside the test which will run the test
   `





