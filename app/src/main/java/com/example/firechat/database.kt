package com.example.firechat

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class, Chat::class], version = 1)
abstract class database : RoomDatabase() {

    abstract fun getdao(): dao

    companion object {
        @Volatile
        private var instance: database? = null
        private var lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            database::class.java,
            "firechat1.db"
        ).fallbackToDestructiveMigration().build()

    }
}