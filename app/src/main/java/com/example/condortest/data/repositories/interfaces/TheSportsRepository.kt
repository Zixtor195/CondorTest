package com.example.condortest.data.repositories.interfaces

import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfoList
import kotlinx.coroutines.flow.Flow

interface TheSportsRepository {
    suspend fun getLeagueTeams(leagueName: String): Flow<NetworkResult<TeamInfoList>>

    suspend fun getTeamLastFiveEvents(idTeam: String): Flow<NetworkResult<TeamEventList>>
}