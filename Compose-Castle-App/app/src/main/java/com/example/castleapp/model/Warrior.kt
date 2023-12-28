package com.example.castleapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

abstract class Warrior {
    var location by mutableFloatStateOf(0f)
    abstract val delayTime: Long

    abstract suspend fun moveToCastle()
    abstract fun returnToHome()
}

class Knight : Warrior() {
    override val delayTime: Long = 500
    override suspend fun moveToCastle() {

        while (location in 0f..99f) {
            delay(delayTime)
            location += 3
        }
    }

    override fun returnToHome() {
        location = 0f
    }
}

class Cavalry : Warrior() {
    override val delayTime: Long = 300
    override suspend fun moveToCastle() {
        while (location in 0f..99f) {
            delay(delayTime)
            location += 5
        }
    }

    override fun returnToHome() {
        location = 0f
    }
}