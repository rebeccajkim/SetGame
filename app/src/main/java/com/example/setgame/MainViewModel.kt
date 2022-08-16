package com.example.setgame

import androidx.compose.runtime.mutableStateListOf
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
    val cardsSelected = mutableStateListOf<Boolean>()
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
        cardsLeft.clear()
        cardsLeft.addAll(deckClass.populateDeck())
        addCards(12)
        for (i in 0..14) {
            cardsSelected.add(false)
        }
    }
    fun addCards(numCards: Int) {
        _cardsShown.value = (_cardsShown.value + cardsLeft.asSequence().shuffled().take(numCards)).toMutableList()
        cardsLeft.removeIf { i -> _cardsShown.value.contains(i) }
    }
    fun onCardSelected(i: Int) {
        cardsSelected[i] = !cardsSelected[i]
        if (cardsSelected[i]) {
            numCardsSelected += 1
        }
        else {
            numCardsSelected -= 1
        }
        if (numCardsSelected == 3) {
            numCardsSelected = 0
            val listIndices = mutableListOf<Int>()
            for (i in 0..14) {
                if (cardsSelected[i]) {
                    listIndices.add(i)
                    cardsSelected[i] = false
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
        else if (_cardsShown.value.size == 15) {
            _validateSetText.value = "There are already 15 cards shown!"
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
}