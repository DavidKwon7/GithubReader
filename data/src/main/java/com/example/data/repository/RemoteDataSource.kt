package com.example.data.repository

import com.example.data.model.UserDataModel

interface RemoteDataSource {

    suspend fun getRepos(owner: String): List<UserDataModel>

}