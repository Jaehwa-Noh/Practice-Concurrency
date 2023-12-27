package com.example.castleapp.ui

import androidx.lifecycle.ViewModel
import com.example.castleapp.model.Warrior
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CastleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CastleUiState())
    val uiState: StateFlow<CastleUiState> = _uiState.asStateFlow()

    fun callWarriors() {
        _uiState.update { currentState ->
            currentState.copy(
                isCalled = true
            )
        }
    }

    fun returnWarriors() {
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