package com.example.setgame

class CardClass(): CardInterface {
    private lateinit var color: CardInterface.ColorValue
    private lateinit var shading: CardInterface.ShadingValue
    private lateinit var shape: CardInterface.ShapeValue
    private lateinit var number: CardInterface.NumberValue

    fun setColor(color: CardInterface.ColorValue) {
        this.color = color
    }
    fun setShading(shading: CardInterface.ShadingValue) {
        this.shading = shading
    }
    fun setShape(shape: CardInterface.ShapeValue) {
        this.shape = shape
    }
    fun setNumber(number: CardInterface.NumberValue) {
        this.number = number
    }

    fun getColor(): CardInterface.ColorValue {
        return color
    }
    fun getShading(): CardInterface.ShadingValue {
        return shading
    }
    fun getShape(): CardInterface.ShapeValue {
        return shape
    }
    fun getNumber(): CardInterface.NumberValue {
        return number
    }
}