package br.com.cwi.freegames.domain.entity

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String?,
    val genre: String?,
    val description: String?,
    val platform: String?,
    val game_url: String?,
    val publisher: String?,
    val developer: String?,
    val release_date: String?,
    val min_system_requirements: GameMinSystemRequirements?,
    var isInPlayLater: Boolean = false
)