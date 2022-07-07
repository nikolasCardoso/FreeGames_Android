package br.com.cwi.freegames.data.repository

import br.com.cwi.freegames.data.network.FreeGamesApi
import br.com.cwi.freegames.data.network.mapper.toEntity
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.entity.GameDetails
import br.com.cwi.freegames.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepositoryImpl(
    private val api: FreeGamesApi
): GameRepository {

    override suspend fun getGames(): List<Game> {
        return withContext(Dispatchers.IO){
            api.getGames().map { game ->
                game.toEntity()
            }
        }
    }

    override suspend fun getGameDetails(id: Int): GameDetails {
        return withContext(Dispatchers.IO){
            api.getGameDetails(id).toEntity()
        }
    }

}