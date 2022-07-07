package br.com.cwi.freegames.domain.entity

data class GameDetails(
    val id: Int,
    val title: String,
    val thumbnail: String?,
    val short_description: String?,
    val game_url: String?,
    val genre: String?,
    val platform: String?,
    val publisher: String?,
    val developer: String?,
    val release_date: String?,
    val min_system_requirements: GameMinSystemRequirements?
)