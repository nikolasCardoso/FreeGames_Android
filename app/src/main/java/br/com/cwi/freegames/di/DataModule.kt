package br.com.cwi.freegames.di

import br.com.cwi.freegames.data.network.RetrofitConfig
import br.com.cwi.freegames.data.repository.GameRepositoryImpl
import br.com.cwi.freegames.domain.repository.GameRepository
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitConfig.service }

    factory<GameRepository> { GameRepositoryImpl(get()) }
}