package com.example.setgame

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.setgame.DeckRepo.SetResult.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val deckClass: DeckRepo,
): ViewModel() {
    private val cardsLeft = mutableListOf<CardClass>()
    private val _cardsShown = MutableStateFlow(mutableListOf<CardClass>())
    val cardsShown: StateFlow<List<CardClass>> = _cardsShown
    private val _cardsSelected = MutableStateFlow(mutableListOf<Boolean>())
    val cardsSelected: StateFlow<List<Boolean>> = _cardsSelected
    private var numCardsSelected = 0
    private val _validateSetText = MutableStateFlow<String>("")
    val validateSetText: StateFlow<String> = _validateSetText
    private val _playerOneScore = MutableStateFlow<Int>(0)
    val playerOneScore: StateFlow<Int> = _playerOneScore
    private val _playerTwoScore = MutableStateFlow<Int>(0)
    val playerTwoScore: StateFlow<Int> = _playerTwoScore
    private val _whichPlayerButtonsVisible = MutableStateFlow<Boolean>(false)
    val whichPlayerButtonsVisible: StateFlow<Boolean> = _whichPlayerButtonsVisible
    private val _restartVisible = MutableStateFlow<Boolean>(false)
    val restartVisible: StateFlow<Boolean> = _restartVisible

    fun startGame() {
        cardsLeft.addAll(deckClass.populateDeck())
        addCards(12)
        for (i in 0..15) {
            _cardsSelected.value = (_cardsSelected.value + false).toMutableList()
        }
    }
    fun addCards(numCards: Int) { //12 cards at first, 3 if no sets, 3 to replace set
        _cardsShown.value = (_cardsShown.value + cardsLeft.asSequence().shuffled().take(numCards)).toMutableList()
        cardsLeft.removeIf { i -> _cardsShown.value.contains(i) }
    }
    fun onCardSelected(i: Int) {
        if (!_cardsSelected.value[i]) {
            _cardsSelected.value[i] = true
            numCardsSelected += 1
        }
        else {
            _cardsSelected.value[i] = false
            numCardsSelected -= 1
        }
        var v = _cardsSelected.value
        Log.i("idk", "$v")
        if (numCardsSelected == 3) {
            numCardsSelected = 0
            val listIndices = mutableListOf<Int>()
            for (i in 0..15) {
                if (_cardsSelected.value[i]) {
                    listIndices.add(i)
                    _cardsSelected.value[i] = false
                }
            }
            validateSet(mutableListOf<CardClass>(_cardsShown.value[listIndices[0]],
                    _cardsShown.value[listIndices[1]], _cardsShown.value[listIndices[2]]))
        }
    }
    fun validateSet(set: MutableList<CardClass>) {
        val result = deckClass.validateSet(set)
        when (result) {
            WrongColor -> {
                _validateSetText.value = "Not a Set! The colors should be all the same or all different!"
            }
            WrongShading -> {
                _validateSetText.value = "Not a Set! The shadings should be all the same or all different!"
            }
            WrongShape -> {
                _validateSetText.value = "Not a Set! The shapes should be all the same or all different!"
            }
            WrongNumber -> {
                _validateSetText.value = "Not a Set! The numbers should be all the same or all different!"
            }
            Correct -> {
                _validateSetText.value = "Set! Which player are you?"
                _whichPlayerButtonsVisible.value = true
                _cardsShown.value = (_cardsShown.value - set).toMutableList()
                if (_cardsShown.value.size == 9 && cardsLeft.size > 0) {
                    addCards(3)
                }
            }
        }
    }
    fun onPlayerOneClicked() {
        _playerOneScore.value = _playerOneScore.value + 3
        _whichPlayerButtonsVisible.value = false
        _validateSetText.value = "Good job Player One!"
    }
    fun onPlayerTwoClicked() {
        _playerTwoScore.value = _playerTwoScore.value + 3
        _whichPlayerButtonsVisible.value = false
        _validateSetText.value = "Good job Player Two!"
    }
    fun onNoSetClicked() {
        if (cardsLeft.size == 0) {
            if (_playerOneScore.value > _playerTwoScore.value) {
                _validateSetText.value = "No more possible Sets! Player One wins!"
            }
            else if (_playerOneScore.value < _playerTwoScore.value) {
                _validateSetText.value = "No more possible Sets! Player Two wins!"
            }
            else {
                _validateSetText.value = "No more possible Sets! It's a tie!"
            }
            _restartVisible.value = true
        }
        else {
            addCards(3)
        }
    }
    fun onRestartClicked() {
        _cardsShown.value = mutableListOf<CardClass>()
        _validateSetText.value = ""
        _playerOneScore.value = 0
        _playerTwoScore.value = 0
        startGame()
        _restartVisible.value = false
    }
    //clean up padding nums, clean up strings, clean up design/font/color, better pics (hard to see striped vs solid)
    //card border wont turn red (turns red when no sets button), always start with same cards idk, testing
    //validation screen other fragment, later animation for putting new cards
}