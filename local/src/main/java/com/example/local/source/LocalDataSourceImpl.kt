package com.example.local.source

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.data.repository.LocalDataSource
import com.example.local.database.FavoriteDAO
import com.example.local.model.UserLocalModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val favoriteDAO: FavoriteDAO,
    private val userMapper: Mapper<UserLocalModel, UserDataModel>
) : LocalDataSource {
    override suspend fun insert(favoriteModel: UserDataModel) {
        val userLocalModel = userMapper.to(favoriteModel)
        return favoriteDAO.insert(userLocalModel)
    }

    override suspend fun getAllData(): List<UserDataModel> {
        val userDataList = favoriteDAO.getAllData()
        return userMapper.fromList(userDataList)
    }

    override suspend fun deleteFav(favModel: UserDataModel) {
        val userLocalModel = userMapper.to(favModel)
        return favoriteDAO.deleteFav(userLocalModel)
    }

    override suspend fun deleteAll() {
        return favoriteDAO.deleteAll()
    }
}