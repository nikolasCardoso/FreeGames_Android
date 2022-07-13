package br.com.cwi.freegames.data.repository

import br.com.cwi.freegames.data.database.dao.ProfileDao
import br.com.cwi.freegames.data.database.mapper.toEntity
import br.com.cwi.freegames.data.database.mapper.toUser
import br.com.cwi.freegames.domain.entity.User
import br.com.cwi.freegames.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: ProfileDao
): UserRepository {
    override fun addProfile(user: User) {
        dao.addProfile(user.toEntity())
    }

    override fun getUser(): User? {
        return dao.getUser()?.toUser()
    }

    override fun isProfileNotExists(): Boolean {
        return dao.isProfileNotExists()
    }

    override fun editProfile(user: User) {
        dao.editProfile(user.toEntity())
    }
}