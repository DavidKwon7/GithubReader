package com.example.data.repository

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.domain.entity.UserEntityModel
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userMapper: Mapper<UserDataModel, UserEntityModel>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Repository {

    override suspend fun getRepos(owner: String): List<UserEntityModel>  {
        val data = remoteDataSource.getRepos(owner)
        return userMapper.fromList(data)
    }
}