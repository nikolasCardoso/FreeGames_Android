package br.com.cwi.freegames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnail: String?,
    val genre: String?,
    val description: String?,
    val platform: String?
)