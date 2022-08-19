package com.example.githubreader.ui.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Mapper
import com.example.domain.entity.UserEntityModel
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.usecase.GetGithubReposUseCase
import com.example.githubreader.model.UserPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getGithubReposUseCase: GetGithubReposUseCase,
    private val userMapper: Mapper<UserEntityModel, UserPresentationModel>
): ViewModel() {

    private var _githubUserLiveData = MutableLiveData<List<UserPresentationModel>>()
    val githubUserLiveData: LiveData<List<UserPresentationModel>> get() = _githubUserLiveData

    fun getRepos(owner: String) = viewModelScope.launch(Dispatchers.IO) {
        getGithubReposUseCase.invoke(owner).let {
            _githubUserLiveData.postValue(userMapper.fromList(it))
        }
    }

}