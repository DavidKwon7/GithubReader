package com.example.githubreader

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.media.metrics.Event
import android.util.EventLog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.domain.usecase.GetGithubReposUseCase
import com.example.githubreader.mapper.UserDomainPresentationMapper
import com.example.githubreader.ui.feature.search.SearchViewModel
import com.example.githubreader.utils.TestDataGenerator
import com.google.common.truth.Truth
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
internal class SearchViewModelTest{

    @get:Rule
    var instanceTaskExcutorRule = InstantTaskExecutorRule()

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

}