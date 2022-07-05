package br.com.cwi.freegames.data.network

import br.com.cwi.freegames.data.network.entity.GameResponse
import retrofit2.http.GET

interface FreeGamesApi {

    @GET("/api/games")
    suspend fun getGames(): List<GameResponse>
}
