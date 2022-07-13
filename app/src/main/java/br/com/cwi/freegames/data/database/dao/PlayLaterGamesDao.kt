package br.com.cwi.freegames.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.cwi.freegames.data.database.entity.GameEntity

@Dao
interface PlayLaterGamesDao {

    @Insert
    fun addGame(gameEntity: GameEntity)

    @Delete
    fun deleteGame(gameEntity: GameEntity)

    @Query("SELECT * FROM GameEntity")
    fun getAllGames(): MutableList<GameEntity>?

    @Query("SELECT * FROM GameEntity WHERE title LIKE '%' || :word || '%' ")
    fun getFilteredTitleGames(word: String?): MutableList<GameEntity>?
}