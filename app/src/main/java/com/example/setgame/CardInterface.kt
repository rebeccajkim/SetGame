package com.example.setgame

interface CardInterface {
    enum class ColorValue() {
        RED, GREEN, PURPLE
    }
    enum class ShapeValue() {
        DIAMOND, OVAL, SQUIGGLE
    }
    enum class ShadingValue() {
        OPEN, STRIPED, SOLID
    }
    enum class NumberValue() {
        ONE, TWO, THREE
    }
}