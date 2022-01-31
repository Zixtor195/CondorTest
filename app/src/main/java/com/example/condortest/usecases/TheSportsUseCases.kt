package com.example.condortest.usecases

import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.data.repositories.interfaces.TheSportsRepository
import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfoList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamListUseCase @Inject constructor(private val theSportsRepository: TheSportsRepository) {
    suspend fun invoke(leagueName: String): Flow<NetworkResult<TeamInfoList>> {
        return theSportsRepository.getLeagueTeams(leagueName)
    }
}

class GetTeamLastFiveEventsUseCase @Inject constructor(private val theSportsRepository: TheSportsRepository) {
    suspend fun invoke(idTeam: String): Flow<NetworkResult<TeamEventList>> {
        return theSportsRepository.getTeamLastFiveEvents(idTeam)
    }
}