package br.com.cwi.freegames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val name: String,
    val email: String,
    val photo: String
){
    @PrimaryKey
    var id: Int = 1
}