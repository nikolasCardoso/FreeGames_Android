package br.com.cwi.freegames.data.network

import br.com.cwi.freegames.data.network.entity.GameDetailsResponse
import br.com.cwi.freegames.data.network.entity.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGamesApi {

    @GET("/api/games")
    suspend fun getGames(): List<GameResponse>

    @GET("/api/game")
    suspend fun getGameDetails(@Query("id") id: Int): GameDetailsResponse
}
