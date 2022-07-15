package br.com.cwi.freegames.data.network.entity

import com.squareup.moshi.Json

data class GameDetailsResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnail: String?,
    @Json(name = "short_description") val description: String?,
    @Json(name = "game_url") val gameUrl: String?,
    @Json(name = "genre") val genre: String?,
    @Json(name = "platform") val platform: String?,
    @Json(name = "publisher") val publisher: String?,
    @Json(name = "developer") val developer: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "minimum_system_requirements") val minSystemRequirements: MinSystemRequirementsResponse?
)