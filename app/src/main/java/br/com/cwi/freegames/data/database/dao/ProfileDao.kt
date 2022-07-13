package br.com.cwi.freegames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.cwi.freegames.data.database.entity.UserEntity

@Dao
interface ProfileDao {

    @Insert
    fun addProfile(userEntity: UserEntity)

    @Update
    fun editProfile(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = 1")
    fun getUser(): UserEntity?

    @Query("SELECT NOT EXISTS(SELECT * FROM UserEntity WHERE id = 1)")
    fun isProfileNotExists(): Boolean
}