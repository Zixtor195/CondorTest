package com.example.condortest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.models.TeamInfoList
import com.example.condortest.usecases.GetTeamListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    application: Application,
    private val getTeamListUseCase: GetTeamListUseCase,
) : AndroidViewModel(application) {

    private val _getTeamList: MutableLiveData<NetworkResult<TeamInfoList>> = MutableLiveData()
    val getTeamList: LiveData<NetworkResult<TeamInfoList>> = _getTeamList

    fun getTeamList(leagueName: String) =
        viewModelScope.launch {
            getTeamListUseCase.invoke(leagueName).collect { values ->
                _getTeamList.value = values
            }
        }
}
