package com.example.condortest.data.repositories

import com.example.condortest.base.di.utilities.BaseApiResponse
import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.data.datasources.interfaces.TheSportsRemoteDataSource
import com.example.condortest.data.repositories.interfaces.TheSportsRepository
import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TheSportsRepositoryImp @Inject constructor(
    private val theSportsRemoteDataSource: TheSportsRemoteDataSource,
) : TheSportsRepository, BaseApiResponse() {

    override suspend fun getLeagueTeams(leagueName: String): Flow<NetworkResult<TeamInfoList>> {
        return flow {
            emit(safeApiCall { theSportsRemoteDataSource.getLeagueTeams(leagueName) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamLastFiveEvents(idTeam: String): Flow<NetworkResult<TeamEventList>> {
        return flow {
            emit(safeApiCall { theSportsRemoteDataSource.getTeamLastFiveEvents(idTeam) })
        }.flowOn(Dispatchers.IO)
    }

}