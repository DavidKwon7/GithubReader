package com.example.local.database

import androidx.room.*
import com.example.local.model.UserLocalModel

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteModel: UserLocalModel)

    @Query("SELECT * FROM favoriteModel")
    suspend fun getAllData(): List<UserLocalModel>

    @Delete
    suspend fun deleteFav(favoriteModel: UserLocalModel)

    @Query("DELETE FROM favoriteModel")
    suspend fun deleteAll()
}