package br.com.cwi.freegames.data.network.mapper

import br.com.cwi.freegames.data.network.entity.GameResponse
import br.com.cwi.freegames.domain.entity.Game

fun GameResponse.toGame() = Game(
    id, title, thumbnail , genre, description, platform
)

fun Game.toEntity() = GameResponse(
    id, title, thumbnail, genre, description, platform
)