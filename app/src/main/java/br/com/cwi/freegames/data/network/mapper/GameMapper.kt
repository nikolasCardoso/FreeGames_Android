package br.com.cwi.freegames.data.network.mapper

import br.com.cwi.freegames.data.network.entity.GameDetailsResponse
import br.com.cwi.freegames.data.network.entity.GameResponse
import br.com.cwi.freegames.data.network.entity.MinSystemRequirementsResponse
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.entity.GameDetails
import br.com.cwi.freegames.domain.entity.GameMinSystemRequirements

fun GameResponse.toEntity() = Game(
    id, title, thumbnail , genre, description, platform
)

fun GameDetailsResponse.toEntity() = GameDetails(
    id,
    title,
    thumbnail,
    short_description,
    game_url,
    genre,
    platform,
    publisher,
    developer,
    release_date,
    min_system_requirements?.toEntity()
)

fun MinSystemRequirementsResponse.toEntity() = GameMinSystemRequirements(
    os, processor, memory, graphics, storage
)