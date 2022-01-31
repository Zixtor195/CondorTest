package com.example.condortest.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.models.TeamEventList
import com.example.condortest.usecases.GetTeamLastFiveEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    application: Application,
    private val getTeamLastFiveEventsUseCase: GetTeamLastFiveEventsUseCase,
) : AndroidViewModel(application) {

    private val _getTeamLastFiveEvents: MutableLiveData<NetworkResult<TeamEventList>> = MutableLiveData()
    val getTeamLastFiveEvents: LiveData<NetworkResult<TeamEventList>> = _getTeamLastFiveEvents

    fun getTeamLastFiveEvents(idTeam: String) =
        viewModelScope.launch {
            getTeamLastFiveEventsUseCase.invoke(idTeam).collect { values ->
                Log.d("TeamLastFiveEvents", idTeam)
                _getTeamLastFiveEvents.value = values
            }
        }

}