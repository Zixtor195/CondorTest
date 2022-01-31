package com.example.condortest.data.datasources.interfaces

import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfoList
import retrofit2.Response

interface TheSportsRemoteDataSource {
    suspend fun getLeagueTeams(leagueName: String): Response<TeamInfoList>

    suspend fun getTeamLastFiveEvents(idTeam: String): Response<TeamEventList>
}