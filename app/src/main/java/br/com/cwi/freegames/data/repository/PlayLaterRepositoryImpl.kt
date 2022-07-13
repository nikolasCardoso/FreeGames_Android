package br.com.cwi.freegames.data.repository

import br.com.cwi.freegames.data.database.dao.PlayLaterGamesDao
import br.com.cwi.freegames.data.database.mapper.toEntity
import br.com.cwi.freegames.data.database.mapper.toGame
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.repository.PlayLaterRepository

class PlayLaterRepositoryImpl(
    private val dao: PlayLaterGamesDao
): PlayLaterRepository {

    override fun addGame(game: Game) {
        dao.addGame(game.toEntity())
    }

    override fun deleteGame(game: Game) {
        dao.deleteGame(game.toEntity())
    }

    override fun getAllGames(): MutableList<Game>? {
        return  dao.getAllGames()?.map {
            it.toGame()
        }?.toMutableList()
    }

    override fun getFilteredTitleGames(word: String?): MutableList<Game>? {
        return dao.getFilteredTitleGames(word)?.map {
            it.toGame()
        }?.toMutableList()
    }

}