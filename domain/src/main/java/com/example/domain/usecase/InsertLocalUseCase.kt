package com.example.domain.usecase

import com.example.domain.entity.UserEntityModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertLocalUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(userEntityModel: UserEntityModel) {
        return repository.insert(userEntityModel)
    }
}