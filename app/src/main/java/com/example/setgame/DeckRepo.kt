package com.example.setgame

interface DeckRepo {
    fun populateDeck(): MutableList<CardClass>
    fun validateSet(set: MutableList<CardClass>): SetResult

    enum class SetResult() {
        WrongColor, WrongShading, WrongShape, WrongNumber, Correct
    }
}