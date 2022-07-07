package br.com.cwi.freegames.domain.entity

data class GameMinSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)