package com.example.remote.api

import com.example.remote.model.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users/{owner}/repos")
    suspend fun getRepos(
        @Path("owner") owner: String
    ) : List<UserRemoteModel>
}