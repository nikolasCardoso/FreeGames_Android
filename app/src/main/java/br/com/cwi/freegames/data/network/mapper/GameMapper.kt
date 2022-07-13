package br.com.cwi.freegames.data.network.mapper

import br.com.cwi.freegames.data.network.entity.GameDetailsResponse
import br.com.cwi.freegames.data.network.entity.GameResponse
import br.com.cwi.freegames.data.network.entity.MinSystemRequirementsResponse
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.entity.GameMinSystemRequirements

fun GameResponse.toDomain() = Game(
    id = id,
    title = title,
    thumbnail = thumbnail ,
    genre = genre,
    description = description,
    platform = platform,
    game_url = null,
    publisher = null,
    developer = null,
    release_date = null,
    min_system_requirements = null
)

fun GameDetailsResponse.toDomain() = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    genre = genre,
    description = description,
    platform = platform,
    game_url = game_url,
    publisher = publisher,
    developer = developer,
    release_date = release_date,
    min_system_requirements = min_system_requirements?.toDomain()
)

fun MinSystemRequirementsResponse.toDomain() = GameMinSystemRequirements(
    os, processor, memory, graphics, storage
)