package com.example.domain.usecase

import com.example.domain.entity.UserEntityModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetGithubReposUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(owner: String): List<UserEntityModel> {
        return repository.getRepos(owner)
    }
}