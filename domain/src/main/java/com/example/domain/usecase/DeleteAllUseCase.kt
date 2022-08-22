package com.example.domain.usecase

import com.example.domain.repository.Repository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke() {
        repository.deleteAll()
    }
}