package com.example.condortest.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.condortest.base.di.utilities.Constants.Companion.LA_LIGA_NAME
import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.databinding.FragmentTeamListBinding
import com.example.condortest.models.TeamInfoList
import com.example.condortest.models.TeamInfo
import com.example.condortest.viewmodels.TeamListViewModel
import com.example.condortest.views.adapters.TeamListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamListFragment : Fragment(), TeamListAdapter.TeamListener {

    private val viewModel: TeamListViewModel by viewModels()
    private lateinit var fragmentTeamListBinding: FragmentTeamListBinding
    private lateinit var teamListAdapter: TeamListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTeamListBinding = FragmentTeamListBinding.inflate(inflater, container, false)
        return fragmentTeamListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerViewTeamList()
        getTeamList()
        super.onViewCreated(view, savedInstanceState)
    }

    /** GetTeamsFunctions **/

    private fun setUpRecyclerViewTeamList() {
        with(fragmentTeamListBinding.recyclerViewTeams) {
            teamListAdapter = TeamListAdapter(this@TeamListFragment)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = teamListAdapter
        }
    }

    private fun getTeamList() {
        viewModel.getTeamList(LA_LIGA_NAME)
        viewModel.getTeamList.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("TeamListFragment", "Work on success")
                    handleGetTeamsResourceSuccess(response.data)
                }
                is NetworkResult.Loading -> {
                    Log.d("TeamListFragment", "Work on loading")
                    handleGetTeamsResourceLoading()
                }
                is NetworkResult.Error -> {
                    Log.d("TeamListFragment", "Work on error")
                    handleGetTeamsResourceError()
                }
            }
        }
    }

    private fun handleGetTeamsResourceSuccess(leagueTeams: TeamInfoList?) {
        leagueTeams?.let {
            teamListAdapter.updateItems(it.teams)
        }
    }

    private fun handleGetTeamsResourceLoading() {
    }

    private fun handleGetTeamsResourceError() {
    }

    /** On Click Functions **/

    override fun onClickTeam(team: TeamInfo) {
        findNavController().navigate(TeamListFragmentDirections.actionNavigationTeamListToNavigationTeamDetails(
            team.idTeam,
            team.strTeam,
            team.strDescriptionEN,
            team.intFormedYear,
            team.strTeamJersey,
            team.strTeamBadge,
            team.strWebsite,
            team.strFacebook,
            team.strTwitter,
            team.strInstagram
        ))

    }


}