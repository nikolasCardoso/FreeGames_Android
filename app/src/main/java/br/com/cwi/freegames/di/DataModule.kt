package br.com.cwi.freegames.di

import br.com.cwi.freegames.data.database.AppDatabase
import br.com.cwi.freegames.data.network.RetrofitConfig
import br.com.cwi.freegames.data.repository.GameRepositoryImpl
import br.com.cwi.freegames.data.repository.PlayLaterRepositoryImpl
import br.com.cwi.freegames.data.repository.UserRepositoryImpl
import br.com.cwi.freegames.domain.repository.GameRepository
import br.com.cwi.freegames.domain.repository.PlayLaterRepository
import br.com.cwi.freegames.domain.repository.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitConfig.service }
    single { AppDatabase.create(androidApplication()).getPlayLaterGamesDao() }
    single { AppDatabase.create(androidApplication()).getProfileDao() }

    factory<GameRepository> { GameRepositoryImpl(get()) }
    factory<PlayLaterRepository> { PlayLaterRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get())  }
}