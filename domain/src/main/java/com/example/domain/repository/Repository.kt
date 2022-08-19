package com.example.domain.repository

import com.example.domain.entity.UserEntityModel

interface Repository {

    suspend fun getRepos(owner: String): List<UserEntityModel>

}