package br.com.cwi.freegames.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.cwi.freegames.data.database.dao.PlayLaterGamesDao
import br.com.cwi.freegames.data.database.dao.ProfileDao
import br.com.cwi.freegames.data.database.entity.GameEntity
import br.com.cwi.freegames.data.database.entity.UserEntity

@Database(entities = [GameEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getPlayLaterGamesDao(): PlayLaterGamesDao
    abstract fun getProfileDao(): ProfileDao

    companion object {
        private const val DATABASE_NAME = "freegames-app"

        fun create(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}