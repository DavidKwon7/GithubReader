package com.example.domain

import com.example.domain.repository.Repository
import com.example.domain.usecase.GetGithubReposUseCase
import com.example.domain.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class GetGithubReposUseCaseTest {

    @MockK
    private lateinit var repository: Repository
    private lateinit var getGithubReposUseCase: GetGithubReposUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getGithubReposUseCase = GetGithubReposUseCase(repository)
    }

    @Test
    fun test_get_github_repos_success() = runTest {

        val userData = TestDataGenerator.generateUser()

        coEvery { repository.getRepos(any()) } returns userData

        val result = getGithubReposUseCase.invoke("example")
        result.let {
            Truth.assertThat(it).containsExactlyElementsIn(userData)
        }
        coVerify { repository.getRepos(any()) }

    }

    @Test(expected = Exception::class)
    fun test_get_github_repos_fail() = runTest {

        val userData = TestDataGenerator.generateUser()

        coEvery { repository.getRepos(any()) } throws Exception()

        getGithubReposUseCase.invoke("example")

        coVerify { repository.getRepos(any()) }

    }
}

