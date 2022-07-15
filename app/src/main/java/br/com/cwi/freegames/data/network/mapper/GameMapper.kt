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

fun List<GameResponse>.toDomain() = this.map { gameResponse -> gameResponse.toDomain() }

fun GameDetailsResponse.toDomain() = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    genre = genre,
    description = description,
    platform = platform,
    game_url = gameUrl,
    publisher = publisher,
    developer = developer,
    release_date = releaseDate,
    min_system_requirements = minSystemRequirements?.toDomain()
)

fun MinSystemRequirementsResponse.toDomain() = GameMinSystemRequirements(
    os, processor, memory, graphics, storage
)