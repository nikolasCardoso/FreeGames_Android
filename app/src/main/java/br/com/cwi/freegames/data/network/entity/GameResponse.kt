package br.com.cwi.freegames.data.network.entity

import com.squareup.moshi.Json

data class GameResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnail: String?,
    @Json(name = "genre") val genre: String?,
    @Json(name = "short_description") val description: String?,
    @Json(name = "platform") val platform: String?
)