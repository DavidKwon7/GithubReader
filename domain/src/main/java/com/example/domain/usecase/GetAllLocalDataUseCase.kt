package com.example.domain.usecase

import com.example.domain.entity.UserEntityModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetAllLocalDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(): List<UserEntityModel> {
        return repository.getAllData()
    }
}