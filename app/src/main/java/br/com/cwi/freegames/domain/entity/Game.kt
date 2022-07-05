package br.com.cwi.freegames.domain.entity

class Game(
    val id: Int,
    val title: String,
    val thumbnail: String?,
    val genre: String?,
    val description: String?,
    val platform: String?
)