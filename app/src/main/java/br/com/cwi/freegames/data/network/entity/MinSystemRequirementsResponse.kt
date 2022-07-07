package br.com.cwi.freegames.data.network.entity

import com.squareup.moshi.Json

data class MinSystemRequirementsResponse(
    @Json(name = "os") val os: String,
    @Json(name = "processor") val processor: String,
    @Json(name = "memory") val memory: String,
    @Json(name = "graphics") val graphics: String,
    @Json(name = "storage") val storage: String
)