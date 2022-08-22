package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.UserDataModel
import com.example.domain.entity.UserEntityModel
import javax.inject.Inject

class UserDataDomainMapper @Inject constructor(): Mapper<UserDataModel, UserEntityModel> {
    override fun from(i: UserDataModel?): UserEntityModel {
        return UserEntityModel(
            id = i?.id,
            name = i?.name,
            url = i?.url
        )
    }

    override fun to(o: UserEntityModel?): UserDataModel {
        return UserDataModel(
            id = o?.id,
            name = o?.name,
            url = o?.url
        )
    }

}