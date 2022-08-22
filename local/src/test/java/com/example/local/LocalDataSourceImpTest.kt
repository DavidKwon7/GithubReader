package com.example.local

import com.example.data.repository.LocalDataSource
import com.example.local.database.FavoriteDAO
import com.example.local.mapper.UserLocalDataMapper
import com.example.local.source.LocalDataSourceImpl
import com.example.local.utils.TestDataGenerator
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
class LocalDataSourceImpTest {

    @MockK
    private lateinit var favoriteDAO: FavoriteDAO

    private val userLocalDataMapper = UserLocalDataMapper()
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSource = LocalDataSourceImpl(
            favoriteDAO = favoriteDAO,
            userMapper = userLocalDataMapper
        )
    }

    @Test
    fun test_get_local_success() = runTest {
        val userData = TestDataGenerator.generateUser()
        coEvery { favoriteDAO.getAllData() } returns userData

        val result = localDataSource.getAllData()
        coVerify { favoriteDAO.getAllData() }

        val expected = userLocalDataMapper.fromList(userData)
        Truth.assertThat(result).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_local_fail() = runTest {

        coEvery { favoriteDAO.getAllData() } throws Exception()

        localDataSource.getAllData()

        coVerify { favoriteDAO.getAllData() }
    }
}