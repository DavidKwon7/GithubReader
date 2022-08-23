package com.example.local.mapper

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.local.model.UserLocalModel
import javax.inject.Inject

class UserLocalDataMapper @Inject constructor() : Mapper<UserLocalModel, UserDataModel> {
    override fun from(i: UserLocalModel?): UserDataModel {
        return UserDataModel(
            uid = i?.uid,
            id = i?.id,
            name = i?.name,
            url = i?.url
        )
    }

    override fun to(o: UserDataModel?): UserLocalModel {
        return UserLocalModel(
            uid = o?.uid,
            id = o?.id,
            name = o?.name,
            url = o?.url

        )
    }
}