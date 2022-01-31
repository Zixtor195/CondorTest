package com.example.condortest.base.di.utilities

import com.example.condortest.base.di.utilities.Constants.Companion.GET_TEAMS_LEAGUE_URL
import com.example.condortest.base.di.utilities.Constants.Companion.GET_TEAM_LAST_EVENTS_URL
import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTheSports {

    @GET(GET_TEAMS_LEAGUE_URL)
    suspend fun getAllTeamsInALeague(@Query("l") league: String): Response<TeamInfoList>

    @GET(GET_TEAM_LAST_EVENTS_URL)
    suspend fun getTeamLastFiveEvents(@Query("id") id: String): Response<TeamEventList>
}