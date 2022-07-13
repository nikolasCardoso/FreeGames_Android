package br.com.cwi.freegames.data.database.mapper

import br.com.cwi.freegames.data.database.entity.UserEntity
import br.com.cwi.freegames.domain.entity.User

fun UserEntity.toUser() = User(
    name, email, photo
)

fun User.toEntity() = UserEntity(
    name, email, photo
)