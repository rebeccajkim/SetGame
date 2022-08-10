package com.example.setgame

import com.example.setgame.Deck
import com.example.setgame.DeckRepo
import com.example.setgame.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            deckClass = get()
        )
    }
    single<DeckRepo> { Deck() }
}