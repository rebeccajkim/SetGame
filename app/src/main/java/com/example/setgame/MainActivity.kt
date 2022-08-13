package com.example.setgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cardsShown by viewModel.cardsShown.collectAsState()
            val cardsSelected = viewModel.cardsSelected
            val validateSetText by viewModel.validateSetText.collectAsState()
            val playerOneScore by viewModel.playerOneScore.collectAsState()
            val playerTwoScore by viewModel.playerTwoScore.collectAsState()
            val whichPlayerButtonsVisible by viewModel.whichPlayerButtonsVisible.collectAsState()
            val restartVisible by viewModel.restartVisible.collectAsState()

            Column {
                TopAppBar(title = {Text(text = "Set")})
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.End) {
                    Button(onClick = viewModel::onNoSetClicked) {Text(text = "No possible Sets")}
                }
                Row(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
                    Column(modifier = Modifier.padding(start = 40.dp, end = 140.dp)) {
                        Text(text = "Player One")
                        Text(text = "$playerOneScore")
                    }
                    Column(modifier = Modifier.padding(end = 60.dp)) {
                        Text(text = "Player Two")
                        Text(text = "$playerTwoScore")
                    }
                }
                if (cardsShown.size > 0) {
                    Row(modifier = Modifier.padding(horizontal = 40.dp).wrapContentSize()) {
                        CardDisplay(cardsShown[0].getColor(), cardsShown[0].getShading(), cardsShown[0].getShape(),
                                cardsShown[0].getNumber(), cardsSelected[0], 0, viewModel::onCardSelected)
                        CardDisplay(cardsShown[1].getColor(), cardsShown[1].getShading(), cardsShown[1].getShape(),
                            cardsShown[1].getNumber(), cardsSelected[1], 1, viewModel::onCardSelected)
                        CardDisplay(cardsShown[2].getColor(), cardsShown[2].getShading(), cardsShown[2].getShape(),
                            cardsShown[2].getNumber(), cardsSelected[2], 2, viewModel::onCardSelected)
                    }
                }
                if (cardsShown.size > 3) {
                    Row(modifier = Modifier.padding(horizontal = 40.dp).wrapContentSize()) {
                        CardDisplay(cardsShown[3].getColor(), cardsShown[3].getShading(), cardsShown[3].getShape(),
                            cardsShown[3].getNumber(), cardsSelected[3], 3, viewModel::onCardSelected)
                        CardDisplay(cardsShown[4].getColor(), cardsShown[4].getShading(), cardsShown[4].getShape(),
                            cardsShown[4].getNumber(), cardsSelected[4], 4, viewModel::onCardSelected)
                        CardDisplay(cardsShown[5].getColor(), cardsShown[5].getShading(), cardsShown[5].getShape(),
                            cardsShown[5].getNumber(), cardsSelected[5], 5, viewModel::onCardSelected)
                    }
                }
                if (cardsShown.size > 6) {
                    Row(modifier = Modifier.padding(horizontal = 40.dp).wrapContentSize()) {
                        CardDisplay(cardsShown[6].getColor(), cardsShown[6].getShading(), cardsShown[6].getShape(),
                            cardsShown[6].getNumber(), cardsSelected[6], 6, viewModel::onCardSelected)
                        CardDisplay(cardsShown[7].getColor(), cardsShown[7].getShading(), cardsShown[7].getShape(),
                            cardsShown[7].getNumber(), cardsSelected[7], 7, viewModel::onCardSelected)
                        CardDisplay(cardsShown[8].getColor(), cardsShown[8].getShading(), cardsShown[8].getShape(),
                            cardsShown[8].getNumber(), cardsSelected[8], 8, viewModel::onCardSelected)
                    }
                }
                if (cardsShown.size > 9) {
                    Row(modifier = Modifier.padding(horizontal = 40.dp).wrapContentSize()) {
                        CardDisplay(cardsShown[9].getColor(), cardsShown[9].getShading(), cardsShown[9].getShape(),
                            cardsShown[9].getNumber(), cardsSelected[9], 9, viewModel::onCardSelected)
                        CardDisplay(cardsShown[10].getColor(), cardsShown[10].getShading(), cardsShown[10].getShape(),
                            cardsShown[10].getNumber(), cardsSelected[10], 10, viewModel::onCardSelected)
                        CardDisplay(cardsShown[11].getColor(), cardsShown[11].getShading(), cardsShown[11].getShape(),
                            cardsShown[11].getNumber(), cardsSelected[11], 11, viewModel::onCardSelected)
                    }
                }
                if (cardsShown.size > 12) {
                    Row(modifier = Modifier.padding(horizontal = 40.dp).wrapContentSize()) {
                        CardDisplay(cardsShown[12].getColor(), cardsShown[12].getShading(), cardsShown[12].getShape(),
                            cardsShown[12].getNumber(), cardsSelected[12], 12, viewModel::onCardSelected)
                        CardDisplay(cardsShown[13].getColor(), cardsShown[13].getShading(), cardsShown[13].getShape(),
                            cardsShown[13].getNumber(), cardsSelected[13], 13, viewModel::onCardSelected)
                        CardDisplay(cardsShown[14].getColor(), cardsShown[14].getShading(), cardsShown[14].getShape(),
                            cardsShown[14].getNumber(), cardsSelected[14], 14, viewModel::onCardSelected)
                    }
                }
                Text(text = validateSetText, modifier = Modifier.padding(8.dp))
                if (whichPlayerButtonsVisible) {
                    Row(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
                        Button(onClick = viewModel::onPlayerOneClicked, modifier = Modifier.padding(
                                start = 40.dp, end = 80.dp)) {Text(text = "Player One")}
                        Button(onClick = viewModel::onPlayerTwoClicked, modifier = Modifier.padding(
                                end = 40.dp)) {Text(text = "Player Two")}
                    }
                }
                if (restartVisible) {
                    Row(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
                        Button(onClick = viewModel::onRestartClicked) {Text(text = "Restart")}
                    }
                }
            }
        }
        viewModel.startGame()
    }
}