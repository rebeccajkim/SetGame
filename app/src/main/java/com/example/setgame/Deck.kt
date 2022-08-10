package com.example.setgame

import com.example.setgame.CardInterface.ColorValue.*
import com.example.setgame.CardInterface.NumberValue.*
import com.example.setgame.CardInterface.ShadingValue.*
import com.example.setgame.CardInterface.ShapeValue.*
import com.example.setgame.DeckRepo.SetResult.*

class Deck: DeckRepo {
    override fun populateDeck(): MutableList<CardClass> {
        val deck = mutableListOf<CardClass>()
        var currCard: CardClass
        for (i in 1..81) {
            currCard = CardClass()
            if (i <= 27) {
                currCard.setColor(RED)
            }
            else if (i <= 54) {
                currCard.setColor(GREEN)
            }
            else {
                currCard.setColor(PURPLE)
            }
            when (i) {
                in 1..9, in 28..36, in 55..63 -> {
                    currCard.setShading(OPEN)
                }
                in 10..18, in 37..45, in 64..72 -> {
                    currCard.setShading(STRIPED)
                }
                else -> {
                    currCard.setShading(SOLID)
                }
            }
            when (i) {
                in 1..3, in 10..12, in 19..21, in 28..30, in 37..39,
                in 46..48, in 55..57, in 64..66, in 73..75 -> {
                    currCard.setShape(DIAMOND)
                }
                in 4..6, in 13..15, in 22..24, in 31..33, in 40..42,
                in 49..51, in 58..60, in 67..69, in 76..78 -> {
                    currCard.setShape(OVAL)
                }
                else -> {
                    currCard.setShape(SQUIGGLE)
                }
            }
            if (i % 3 == 1) {
                currCard.setNumber(ONE)
            }
            else if (i % 3 == 2) {
                currCard.setNumber(TWO)
            }
            else {
                currCard.setNumber(THREE)
            }
            deck.add(currCard)
        }
        return deck
    }

    override fun validateSet(set: MutableList<CardClass>): DeckRepo.SetResult {
        if (!(set[0].getColor() == set[1].getColor() && set[0].getColor() == set[2].getColor()) &&
        !(set[0].getColor() != set[1].getColor() && set[0].getColor() != set[2].getColor() && set[1].getColor() != set[2].getColor())) {
            return WrongColor
        }
        if (!(set[0].getShading() == set[1].getShading() && set[0].getShading() == set[2].getShading()) &&
            !(set[0].getShading() != set[1].getShading() && set[0].getShading() != set[2].getShading() && set[1].getShading() != set[2].getShading())) {
            return WrongShading
        }
        if (!(set[0].getShape() == set[1].getShape() && set[0].getShape() == set[2].getShape()) &&
            !(set[0].getShape() != set[1].getShape() && set[0].getShape() != set[2].getShape() && set[1].getShape() != set[2].getShape())) {
            return WrongShape
        }
        if (!(set[0].getNumber() == set[1].getNumber() && set[0].getNumber() == set[2].getNumber()) &&
            !(set[0].getNumber() != set[1].getNumber() && set[0].getNumber() != set[2].getNumber() && set[1].getNumber() != set[2].getNumber())) {
            return WrongNumber
        }
        return Correct
    }
}