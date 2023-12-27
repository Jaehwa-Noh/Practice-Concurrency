package com.example.castleapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

abstract class Warrior {
    abstract var location: Float

    abstract suspend fun moveToCastle()
    abstract fun returnToHome()
}

class Knight : Warrior() {
    override var location by mutableFloatStateOf(0f)
    override suspend fun moveToCastle() {

        while (location in 0f..99f) {
            delay(500)
            location += 3
        }
    }

    override fun returnToHome() {
        location = 0f
    }
}

class Cavalry : Warrior() {
    override var location by mutableFloatStateOf(0f)
    override suspend fun moveToCastle() {
        while (location in 0f..99f) {
            delay(300)
            location += 5
        }
    }

    override fun returnToHome() {
        location = 0f
    }
}