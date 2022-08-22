package com.example.githubreader

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetGithubReposUseCase
import com.example.githubreader.mapper.UserDomainPresentationMapper
import com.example.githubreader.model.UserPresentationModel
import com.example.githubreader.ui.feature.search.SearchViewModel
import com.example.githubreader.utils.CoroutineTestRule
import com.example.githubreader.utils.TestDataGenerator
import com.google.common.truth.Truth
import dagger.hilt.android.scopes.ViewModelScoped
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
internal class SearchViewModelTest : CoroutineTestRule() {

    @get:Rule
    var instanceTaskExcutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var getGithubReposUseCase: GetGithubReposUseCase

    private val userMapper = UserDomainPresentationMapper()

    private lateinit var searchViewModel: SearchViewModel



    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        searchViewModel = SearchViewModel(
            getGithubReposUseCase = getGithubReposUseCase,
            userMapper = userMapper
        )
    }



    @Test
    fun case2(): Unit = runTest(UnconfinedTestDispatcher()) {
        val userData = TestDataGenerator.generateUser()
        coEvery { getGithubReposUseCase.invoke(any()) } returns userData

        // val expected = searchViewModel.githubUserLiveData.value
        val expected =  searchViewModel.getRepos("d")

        Truth.assertThat(userData).isEqualTo(expected)

        coVerify { searchViewModel.getRepos("") }
    }

    /*@Test
    fun aa123() = runTest {
        val userData = TestDataGenerator.generateUser()
        coEvery { getGithubReposUseCase.invoke(any()) } returns userData
        searchViewModel.githubUserLiveData.observeForever(githubUserObserver)
        searchViewModel.getRepos("a").

    }

    @Test
    fun test_get_remote_success() = runTest {

        val userData = TestDataGenerator.generateUser()

        coEvery { getGithubReposUseCase.invoke(any()) } returns userData

        searchViewModel.getRepos("example").let {
            Truth.assertThat(it).isEqualTo(userData)
        }

        coVerify { getGithubReposUseCase.invoke(any()) }
    }

    @Test
    fun test_get_github_repos_success() = runTest(UnconfinedTestDispatcher()) {
        val userData = TestDataGenerator.generateUser()

        coEvery { getGithubReposUseCase.invoke(any()) } returns userData

        searchViewModel.getRepos("ex").let {
            Truth.assertThat(it).isEqualTo(userData)
        }

        coVerify { getGithubReposUseCase.invoke(any()) }
    }

    @Test
    fun observingDataTest() {
        val userData = TestDataGenerator.generateUser()

        coEvery { getGithubReposUseCase.invoke(any()) } returns userData
        searchViewModel.githubUserLiveData.observeForever {
            searchViewModel.getRepos("ex").let {
                Truth.assertThat(it).isEqualTo(userData)
            }
        }
        coVerify { getGithubReposUseCase.invoke(any()) }
    }
*/
}