package com.example.castleapp.data

import com.example.castleapp.model.Cavalry
import com.example.castleapp.model.Knight

object class WarriorRepository {
    val getCastleWarriors = listOf(
        Knight(),
        Cavalry()
    )
}