package com.example.data.repository

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.domain.entity.UserEntityModel
import com.example.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val userMapper: Mapper<UserDataModel, UserEntityModel>,
) : Repository {

    // remote
    override suspend fun getRepos(owner: String): List<UserEntityModel>  {
        val data = remoteDataSource.getRepos(owner)
        return userMapper.fromList(data)
    }


    // local
    override suspend fun insert(favoriteModel: UserEntityModel) {
        val userLocalModel = userMapper.to(favoriteModel)
        return localDataSource.insert(userLocalModel)
    }

    override suspend fun getAllData(): List<UserEntityModel> {
        val localData = localDataSource.getAllData()
        return userMapper.fromList(localData)
    }

    override suspend fun deleteFav(favModel: UserEntityModel) {
        val userLocalModel = userMapper.to(favModel)
        return localDataSource.deleteFav(userLocalModel)
    }

    override suspend fun deleteAll() {
        return localDataSource.deleteAll()
    }
}