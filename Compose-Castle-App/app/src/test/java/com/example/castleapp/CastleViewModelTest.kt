package com.example.castleapp

import com.example.castleapp.data.WarriorRepository
import com.example.castleapp.model.Warrior
import com.example.castleapp.model.whereAreYou
import com.example.castleapp.ui.CastleViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CastleViewModelTest {

    private val viewModel = CastleViewModel()

    @Test
    fun castleViewModel_InitTest_SetWarriorsSuccess() {
        val currentUiState = viewModel.uiState.value

        assertEquals(WarriorRepository.getCastleWarriors, currentUiState.warriors)
    }

    @Test
    fun castleViewModel_CallButtonClick_CallWarriorsRunSuccess() {
        viewModel.callWarriors()
        val currentUiState = viewModel.uiState.value
        assertEquals(true, currentUiState.isCalled)
    }

    @Test
    fun castleViewModel_ReturnButtonClick_ReturnWarriorsRunSuccess() {
        viewModel.returnWarriors()
        val currentUiState = viewModel.uiState.value
        assertEquals(false, currentUiState.isCalled)
    }

    @Test
    fun castleViewModel_CallAndReturnClickTwice_isCalledSetFalseEdge() {
        repeat(2) {
            viewModel.callWarriors()
            var currentUiState = viewModel.uiState.value
            assertEquals(true, currentUiState.isCalled)

            viewModel.returnWarriors()
            currentUiState = viewModel.uiState.value
            assertEquals(false, currentUiState.isCalled)
        }
    }

    @Test
    fun castleViewModel_PauseButtonClick_isCalledSetFalseSuccess() {
        viewModel.pauseWarriors()
        val currentUiState = viewModel.uiState.value
        assertEquals(false, currentUiState.isCalled)
    }

    @Test
    fun castleViewModel_moveToCastleWarriors_LocationUpdatedSuccess() = runTest {
        val moveJob = launch { viewModel.moveToCastleWarriors() }
        moveJob.join()
        val currentUiState = viewModel.uiState.value
        assertTrue(currentUiState.warriors[0].whereAreYou >= 1)
        assertTrue(currentUiState.warriors[1].whereAreYou >= 1)
    }

    @Test
    fun castleViewModel_returnToHome_LocationWillZeroSuccess() = runTest {
        val moveJob = launch { viewModel.moveToCastleWarriors() }
        moveJob.join()
        val currentUiState = viewModel.uiState.value
        assertTrue(currentUiState.warriors[0].whereAreYou >= 1)
        assertTrue(currentUiState.warriors[1].whereAreYou >= 1)

        resetLocation(currentUiState.warriors)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun castleViewModel_moveToCastleWarriorsPause_LocationUpdateSuccess() = runTest {
        val expectedKnightLocation = 6f
        val expectedCavalryLocation = 15f
        var currentUiState = viewModel.uiState.value

        resetLocation(currentUiState.warriors)

        val moveJob = launch { viewModel.moveToCastleWarriors() }
        advanceTimeBy(1000)
        runCurrent()
        moveJob.cancelAndJoin()
        currentUiState = viewModel.uiState.value
        val warriors = currentUiState.warriors
        assertEquals(expectedKnightLocation, warriors[0].location)
        assertEquals(expectedCavalryLocation, warriors[1].location)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun castleViewModel_moveToCastleWarriorsPauseAndResumed_LocationUpdateSuccess() = runTest {
        val expectedKnightLocation = 6f
        val expectedCavalryLocation = 15f
        repeat(3) {
            val moveJob = launch { viewModel.moveToCastleWarriors() }
            advanceTimeBy(1000)
            runCurrent()
            moveJob.cancelAndJoin()
        }
        val warriors = viewModel.uiState.value.warriors
        assertEquals(expectedKnightLocation * 3, warriors[0].location)
        assertEquals(expectedCavalryLocation * 3, warriors[1].location)
    }

    private fun resetLocation(warriors: List<Warrior>) {
        warriors.forEach {
            it.returnToHome()
        }

        assertEquals(warriors[0].whereAreYou, 0f)
        assertEquals(warriors[1].whereAreYou, 0f)
    }
}