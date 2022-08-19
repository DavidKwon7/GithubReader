package com.example.remote.source

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.GithubApiService
import com.example.remote.model.UserRemoteModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val githubApiService: GithubApiService,
    private val userMapper: Mapper<UserRemoteModel, UserDataModel>
) : RemoteDataSource {

    override suspend fun getRepos(owner: String): List<UserDataModel> {
        val remoteData = githubApiService.getRepos(owner)
        return userMapper.fromList(remoteData)
    }
}