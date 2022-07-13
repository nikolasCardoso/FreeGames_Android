package br.com.cwi.freegames.domain.repository

import br.com.cwi.freegames.domain.entity.Game

interface GameRepository {
    suspend fun getGames(): List<Game>
    suspend fun getGameDetails(id: Int): Game
}