package com.example.castleapp.model

abstract class Warrior {
    var location: Int = 0

    abstract fun moveToCastle()
    abstract fun returnToHome()
}

class Knight(): Warrior() {
    override fun moveToCastle() {

    }

    override fun returnToHome() {

    }
}

class Cavalry(): Warrior() {
    override fun moveToCastle() {

    }

    override fun returnToHome() {

    }
}