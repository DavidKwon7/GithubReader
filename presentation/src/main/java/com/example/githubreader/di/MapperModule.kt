package com.example.githubreader.di

import com.example.common.Mapper
import com.example.data.mapper.UserDataDomainMapper
import com.example.data.model.UserDataModel
import com.example.domain.entity.UserEntityModel
import com.example.githubreader.mapper.UserDomainPresentationMapper
import com.example.githubreader.model.UserPresentationModel
import com.example.remote.mapper.UserRemoteDataMapper
import com.example.remote.model.UserRemoteModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsUserRemoteDataMapper(mapper: UserRemoteDataMapper): Mapper<UserRemoteModel, UserDataModel>

    @Binds
    abstract fun bindsUserDataDomainMapper(mapper: UserDataDomainMapper): Mapper<UserDataModel, UserEntityModel>

    @Binds
    abstract fun bindsUserDomainPresentationMapper(mapper: UserDomainPresentationMapper): Mapper<UserEntityModel, UserPresentationModel>
}