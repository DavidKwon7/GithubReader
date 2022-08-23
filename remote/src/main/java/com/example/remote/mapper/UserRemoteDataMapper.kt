package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.remote.model.UserRemoteModel
import javax.inject.Inject

class UserRemoteDataMapper @Inject constructor(): Mapper<UserRemoteModel, UserDataModel> {
    override fun from(i: UserRemoteModel?): UserDataModel {
        return UserDataModel(
            uid = i?.uid,
            id = i?.id,
            name = i?.name,
            url = i?.url
        )
    }

    override fun to(o: UserDataModel?): UserRemoteModel {
        return UserRemoteModel(
            uid = o?.uid,
            id = o?.id,
            name = o?.name,
            url = o?.url
        )
    }
}