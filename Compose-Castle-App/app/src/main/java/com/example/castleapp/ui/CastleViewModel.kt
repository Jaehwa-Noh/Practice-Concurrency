package com.example.castleapp.ui

import androidx.lifecycle.ViewModel
import com.example.castleapp.data.WarriorRepository
import com.example.castleapp.model.Warrior
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CastleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CastleUiState())
    val uiState: StateFlow<CastleUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                warriors = WarriorRepository.getCastleWarriors,
            )
        }
    }

    fun callWarriors() {
        _uiState.update { currentState ->
            currentState.copy(
                isCalled = true
            )
        }
    }

    suspend fun moveToCastleWarriors() {
        coroutineScope {
            launch { _uiState.value.warriors[0].moveToCastle() }
            launch { _uiState.value.warriors[1].moveToCastle() }
        }
    }

    fun returnWarriors() {
        _uiState.update { currentState ->
            currentState.copy(
                isCalled = false
            )
        }

        _uiState.value.warriors.forEach {
            it.returnToHome()
        }
    }

    fun pauseWarriors() {
        _uiState.update { currentState ->
            currentState.copy(
                isCalled = false
            )
        }
    }
}

data class CastleUiState(
    val warriors: List<Warrior> = emptyList(),
    val isCalled: Boolean = false
)