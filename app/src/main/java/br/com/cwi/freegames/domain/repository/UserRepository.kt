package br.com.cwi.freegames.domain.repository

import br.com.cwi.freegames.domain.entity.User

interface UserRepository {
    fun addProfile(user: User)
    fun getUser(): User?
    fun isProfileNotExists(): Boolean
    fun editProfile(user: User)
}