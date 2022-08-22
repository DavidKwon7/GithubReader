package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.model.UserLocalModel

@Database(
    entities = [UserLocalModel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favDao(): FavoriteDAO
}