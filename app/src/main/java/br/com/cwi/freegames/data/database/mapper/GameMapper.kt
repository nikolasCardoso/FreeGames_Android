package br.com.cwi.freegames.data.database.mapper

import br.com.cwi.freegames.data.database.entity.GameEntity
import br.com.cwi.freegames.domain.entity.Game

fun GameEntity.toGame() = Game(
    id,
    title,
    thumbnail ,
    genre,
    description,
    platform,
    game_url = null,
    publisher = null,
    developer = null,
    release_date = null,
    min_system_requirements = null
)

fun MutableList<GameEntity>.toGame(): MutableList<Game>{
    return this.map { gameEntity -> gameEntity.toGame() }.toMutableList()
}

fun Game.toEntity() = GameEntity(
    id, title, thumbnail , genre, description, platform
)