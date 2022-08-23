package com.example.data

import com.example.data.mapper.UserDataDomainMapper
import com.example.data.repository.LocalDataSource
import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImpl
import com.example.data.utils.TestDataGenerator
import com.example.domain.repository.Repository
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
class RepositoryImpTest {

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @MockK
    private lateinit var localDataSource: LocalDataSource

    private val userMapper = UserDataDomainMapper()

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = RepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            userMapper = userMapper
        )
    }

    @Test
    fun test_get_remote_success() = runTest {

        val userData = TestDataGenerator.generateUser()

        coEvery { remoteDataSource.getRepos(any()) } returns  userData

        val data = repository.getRepos("example")

        data.let {
            Truth.assertThat(it).containsExactlyElementsIn(userMapper.fromList(userData))
        }

        coVerify { remoteDataSource.getRepos(any()) }
    }

    @Test(expected = Exception::class)
    fun test_get_remote_fail() = runTest {

        coEvery { remoteDataSource.getRepos(any()) } throws Exception()

        repository.getRepos("example")

        coVerify { remoteDataSource.getRepos(any()) }
    }
}