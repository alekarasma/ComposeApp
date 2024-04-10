package com.asmaa.composeapp


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun injectHiltRule() {
        hiltRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun pwd_less_than_six_chars_test() {
        // Wait for the splash screen to disappear and then start testing
        composeTestRule.waitUntilDoesNotExist(hasText("Welcome to Orange & Co."), 5000)
        composeTestRule.onNodeWithText("Enter Username").performTextInput("asma")
        composeTestRule.onNodeWithText("Enter Password").performTextInput("abc12")
        composeTestRule.onNodeWithText("Confirm Password").performTextInput("abc12")
        composeTestRule.onNodeWithText("Password less than 6 characters").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigate_to_next_screen_test() {
        //is this a right way to do it? How to test navigation in compose - I will come back to this
        composeTestRule.waitUntilDoesNotExist(hasText("Welcome to Orange & Co."), 5000)
        composeTestRule.onNodeWithText("Enter Username").performTextInput("as")
        composeTestRule.onNodeWithText("Enter Password").performTextInput("abc123")
        composeTestRule.onNodeWithText("Confirm Password").performTextInput("abc123")
        composeTestRule.onNodeWithText("Sign up").performClick()
        composeTestRule.waitUntilDoesNotExist(hasText("Cancel Invite"), 5000)
    }

}

