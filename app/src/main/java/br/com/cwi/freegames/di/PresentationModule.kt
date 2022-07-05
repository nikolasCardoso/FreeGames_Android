package br.com.cwi.freegames.di

import br.com.cwi.freegames.presentation.feature.games.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { GamesViewModel(get()) }
}