package com.example.githubreader.mapper

import com.example.common.Mapper
import com.example.domain.entity.UserEntityModel
import com.example.githubreader.model.UserPresentationModel
import javax.inject.Inject

class UserDomainPresentationMapper @Inject constructor(): Mapper<UserEntityModel, UserPresentationModel> {

    override fun from(i: UserEntityModel?): UserPresentationModel {
        return UserPresentationModel(
            name = i?.name,
            url = i?.url
        )
    }

    override fun to(o: UserPresentationModel?): UserEntityModel {
        return UserEntityModel(
            name = o?.name,
            url = o?.url
        )
    }
}