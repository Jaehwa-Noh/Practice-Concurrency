package com.example.castleapp

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.castleapp.ui.CastleScreen
import com.example.castleapp.ui.theme.CastleAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CastleScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun createCastleScreen() {
        composeTestRule.setContent {
            CastleAppTheme {
                CastleScreen()
            }
        }
    }

    @Test
    fun castleScreen_CallClick_PauseDisplaySuccess() {
        val callButton = composeTestRule.onNodeWithText("Call")
        callButton.performClick()

        val pauseButton = composeTestRule.onNodeWithText("Pause")
        pauseButton.assertIsDisplayed()
    }

    @Test
    fun castleScreen_CallClickAndClickReturn_CallDisplaySuccess() {
        val callButton = composeTestRule.onNodeWithText("Call")
        callButton.performClick()

        val returnButton = composeTestRule.onNodeWithText("Return")
        returnButton.performClick()
        callButton.assertIsDisplayed()
    }

    @Test
    fun castleScreen_CallClickAndClickReturnTwice_CallDisplayEdge() {
        repeat(2) {
            val callButton = composeTestRule.onNodeWithText("Call")
            callButton.performClick()

            val returnButton = composeTestRule.onNodeWithText("Return")
            returnButton.performClick()
            callButton.assertIsDisplayed()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun castleScreen_CallClickAndWaitFull_CallButtonShowAgainSuccess() {

        val callButton = composeTestRule.onNodeWithText("Call")
        callButton.performClick()

        val pauseButton = composeTestRule.onNodeWithText("Pause")
        pauseButton.assertExists()

        composeTestRule.waitUntilDoesNotExist(
            hasText("Pause"), 100000
        )


        callButton.assertIsDisplayed()
        resetProgressBar()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun castleScreen_CallClickAndPauseAndWaitFull_CallButtonShowAgainEdge() {

        val callButton = composeTestRule.onNodeWithText("Call")
        callButton.performClick()

        val pauseButton = composeTestRule.onNodeWithText("Pause")
        pauseButton.performClick()

        callButton.performClick()
        composeTestRule.waitUntilDoesNotExist(
            hasText("Pause"), 100000
        )

        callButton.assertIsDisplayed()
        resetProgressBar()
    }

    private fun resetProgressBar() {
        val returnButton = composeTestRule.onNodeWithText("Return")
        returnButton.assertIsDisplayed()
        returnButton.performClick()
    }
}