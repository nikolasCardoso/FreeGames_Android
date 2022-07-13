package br.com.cwi.freegames.di

import br.com.cwi.freegames.presentation.feature.games.GamesViewModel
import br.com.cwi.freegames.presentation.feature.play_later.PlayLaterViewModel
import br.com.cwi.freegames.presentation.feature.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { GamesViewModel(get(),get()) }
    viewModel { PlayLaterViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}