package com.example.domain.repository

import com.example.domain.entity.UserEntityModel

interface Repository {

    // remote
    suspend fun getRepos(owner: String): List<UserEntityModel>

    // local
    suspend fun insert(favoriteModel: UserEntityModel)

    suspend fun getAllData(): List<UserEntityModel>

    suspend fun deleteFav(favModel: UserEntityModel)

    suspend fun deleteAll()
}