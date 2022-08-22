package com.example.githubreader.ui.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.Mapper
import com.example.common.base.BaseViewModel
import com.example.domain.entity.UserEntityModel
import com.example.domain.usecase.GetGithubReposUseCase
import com.example.domain.usecase.InsertLocalUseCase
import com.example.githubreader.model.UserPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertUseCase: InsertLocalUseCase,
    private val userMapper: Mapper<UserEntityModel, UserPresentationModel>
) : BaseViewModel() {

    private var _favoriteLiveData = MutableLiveData<UserPresentationModel>()
    val favoriteLiveData: LiveData<UserPresentationModel> get() = _favoriteLiveData

    fun insertLocalDB(userEntityModel: UserEntityModel) = viewModelScope.launch(Dispatchers.IO) {
        val localData = userMapper.from(userEntityModel)
        insertUseCase.invoke(userEntityModel).let {
            _favoriteLiveData.postValue(localData)
        }
    }
}