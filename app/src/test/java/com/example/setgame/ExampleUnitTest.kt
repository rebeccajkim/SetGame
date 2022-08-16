package com.example.setgame

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var viewModel: TestMainViewModel

    @Before
    fun setUp() {
        viewModel = TestMainViewModel()
    }

    @Test
    fun randomAddCards() {
        val testCardsLeft = (1..81).toMutableList()
        val resultOne = viewModel.addCards(12, testCardsLeft)
        val resultTwo = viewModel.addCards(12, testCardsLeft)
        print("${resultOne.value} and ${resultTwo.value}")
        assertNotEquals(resultOne, resultTwo)
    }
}
class TestMainViewModel: ViewModel() {
    fun addCards(numCards: Int, cardsLeft: MutableList<Int>): MutableStateFlow<MutableList<Int>> {
        val _cardsShown = MutableStateFlow(mutableListOf<Int>())
        _cardsShown.value = (_cardsShown.value + cardsLeft.asSequence().shuffled().take(numCards)).toMutableList()
        cardsLeft.removeIf { i -> _cardsShown.value.contains(i) }
        return _cardsShown
    }
}