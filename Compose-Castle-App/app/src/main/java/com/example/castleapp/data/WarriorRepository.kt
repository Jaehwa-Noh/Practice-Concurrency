package com.example.castleapp.data

import com.example.castleapp.model.Cavalry
import com.example.castleapp.model.Knight

object WarriorRepository {
    val getCastleWarriors = listOf(
        Knight(delayTime = 500),
        Cavalry(delayTime = 300)
    )
}