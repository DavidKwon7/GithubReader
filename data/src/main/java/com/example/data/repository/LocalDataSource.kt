package com.example.data.repository

import com.example.data.model.UserDataModel

interface LocalDataSource {

    suspend fun insert(favoriteModel: UserDataModel)

    suspend fun getAllData(): List<UserDataModel>

    suspend fun deleteFav(favModel: UserDataModel)

    suspend fun deleteAll()
}