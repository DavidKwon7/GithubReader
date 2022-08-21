package com.example.remote

import com.example.data.repository.RemoteDataSource
import com.example.remote.api.GithubApiService
import com.example.remote.mapper.UserRemoteDataMapper
import com.example.remote.source.RemoteDataSourceImpl
import com.example.remote.utils.TestDataGenerator
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
import org.mockito.ArgumentMatchers

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class RemoteDataSourceImplTest {

    @MockK
    private lateinit var apiService: GithubApiService
    private val userRemoteDateMapper = UserRemoteDataMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
         MockKAnnotations.init(this, relaxUnitFun = true)
        remoteDataSource = RemoteDataSourceImpl(
            githubApiService = apiService,
            userMapper = userRemoteDateMapper
        )
    }

    @Test
    fun test_get_remote_success() = runTest {
        val remoteUser = TestDataGenerator.generateUser()

        coEvery { apiService.getRepos(any()) } returns remoteUser

        val result = remoteDataSource.getRepos("example")

        coVerify { apiService.getRepos(any()) }

        val expected = userRemoteDateMapper.fromList(remoteUser)
        Truth.assertThat(result).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_remote_fail() = runTest {

        // Given
        coEvery { apiService.getRepos(ArgumentMatchers.any()) } throws Exception()

        // When
        remoteDataSource.getRepos("example")

        // Then
        coVerify { apiService.getRepos(ArgumentMatchers.any()) }
    }

}