package com.example.local.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.model.FavoriteModel

interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteModel: FavoriteModel)

    @Query("SELECT * FROM favoriteModel")
    fun getAllData(): List<FavoriteModel>

    @Delete
    suspend fun deleteFav(favoriteModel: FavoriteModel)

    @Query("DELETE FROM favoriteModel")
    suspend fun deleteAll()
}