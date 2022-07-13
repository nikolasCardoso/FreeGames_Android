package br.com.cwi.freegames.domain.repository

import br.com.cwi.freegames.domain.entity.Game

interface PlayLaterRepository {
    fun addGame(game: Game)
    fun deleteGame(game: Game)
    fun getAllGames(): MutableList<Game>?
    fun getFilteredTitleGames(word: String?): MutableList<Game>?
}