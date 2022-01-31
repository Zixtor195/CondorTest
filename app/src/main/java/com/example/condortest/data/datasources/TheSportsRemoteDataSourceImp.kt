package com.example.condortest.data.datasources

import com.example.condortest.base.di.utilities.ApiTheSports
import com.example.condortest.data.datasources.interfaces.TheSportsRemoteDataSource
import javax.inject.Inject


class TheSportsRemoteDataSourceImp @Inject constructor(private val apiTheSports: ApiTheSports) :
    TheSportsRemoteDataSource {

    override suspend fun getLeagueTeams(leagueName: String) = apiTheSports.getAllTeamsInALeague(leagueName)

    override suspend fun getTeamLastFiveEvents(idTeam: String) = apiTheSports.getTeamLastFiveEvents(idTeam)
}