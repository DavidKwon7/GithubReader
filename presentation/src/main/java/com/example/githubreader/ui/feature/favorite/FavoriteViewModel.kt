package com.example.githubreader.ui.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.Mapper
import com.example.common.base.BaseViewModel
import com.example.domain.entity.UserEntityModel
import com.example.domain.usecase.DeleteAllUseCase
import com.example.domain.usecase.DeleteFavUseCase
import com.example.domain.usecase.GetAllLocalDataUseCase
import com.example.githubreader.model.UserPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllLocalDataUseCase: GetAllLocalDataUseCase,
    private val deleteAllUseCase: DeleteAllUseCase,
    private val userMapper: Mapper<UserEntityModel, UserPresentationModel>
) : BaseViewModel() {

    private var _favoriteLiveData = MutableLiveData<List<UserPresentationModel>>()
    val favoriteLiveData: LiveData<List<UserPresentationModel>> get() = _favoriteLiveData

    fun getAllLocalData() = viewModelScope.launch(Dispatchers.IO) {
        getAllLocalDataUseCase.invoke().let { data ->
            _favoriteLiveData.postValue(userMapper.fromList(data))
        }
    }
}