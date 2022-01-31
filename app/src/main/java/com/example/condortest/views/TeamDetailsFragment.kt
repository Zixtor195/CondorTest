package com.example.condortest.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.condortest.base.di.utilities.NetworkResult
import com.example.condortest.databinding.FragmentTeamDetailsBinding
import com.example.condortest.models.TeamEventList
import com.example.condortest.models.TeamInfo
import com.example.condortest.models.TeamInfoList
import com.example.condortest.viewmodels.TeamDetailsViewModel
import com.example.condortest.views.adapters.TeamDetailsAdapter
import com.example.condortest.views.adapters.TeamLinksAdapter
import com.example.condortest.views.adapters.TeamListAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri


@AndroidEntryPoint
class TeamDetailsFragment : Fragment(), TeamDetailsAdapter.TeamDetailsListener, TeamLinksAdapter.TeamLinksListener {


    private val arguments: TeamDetailsFragmentArgs by navArgs()
    private val viewModel: TeamDetailsViewModel by viewModels()
    private lateinit var fragmentTeamDetailsBinding: FragmentTeamDetailsBinding
    private lateinit var teamDetailsAdapter: TeamDetailsAdapter
    private lateinit var teamLinksAdapter: TeamLinksAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTeamDetailsBinding = FragmentTeamDetailsBinding.inflate(inflater, container, false)
        return fragmentTeamDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerTeamLastFiveEvents()
        setUpRecyclerTeamLinks()
        getTeamLastFiveEvents(arguments.teamId)
        bindArgs(fragmentTeamDetailsBinding)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerTeamLastFiveEvents() {
        with(fragmentTeamDetailsBinding.recyclerViewDetailsNextEvents) {
            teamDetailsAdapter = TeamDetailsAdapter(this@TeamDetailsFragment)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = teamDetailsAdapter
        }
    }

    private fun setUpRecyclerTeamLinks() {
        with(fragmentTeamDetailsBinding.recyclerViewDetailsSocialNetworks) {
            teamLinksAdapter = TeamLinksAdapter(this@TeamDetailsFragment)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = teamLinksAdapter
        }
    }

    private fun bindArgs(binding: FragmentTeamDetailsBinding) {
        Glide.with(this).load(arguments.teamJersey).into(binding.imageViewDetailTeamJersey)
        Glide.with(this).load(arguments.teamBadge).into(binding.imageViewDetailsTeamBadge)
        binding.textViewDetailsTeamName.text = arguments.teamName
        binding.textViewDetailsTeamDescription.text = arguments.teamDescription
        binding.textViewDetailsTeamFoundation.text = arguments.teamFoundation

        val a: MutableList<String> = mutableListOf()
        a.add(arguments.teamWebsite)
        a.add(arguments.teamTwitter)
        a.add(arguments.teamInstagram)
        a.add(arguments.teamFacebook)
        teamLinksAdapter.updateItems(a.toList())
    }

    private fun getTeamLastFiveEvents(idTeam: String) {
        viewModel.getTeamLastFiveEvents(idTeam)
        viewModel.getTeamLastFiveEvents.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("TeamLastFiveEvents", "Work on success")
                    handleGetTeamLastFiveEventsResourceSuccess(response.data)
                }
                is NetworkResult.Loading -> {
                    Log.d("TeamLastFiveEvents", "Work on loading")
                    handleGetTeamLastFiveEventsResourceLoading()
                }
                is NetworkResult.Error -> {
                    Log.d("TeamLastFiveEvents", "Work on error")
                    handleGetTeamLastFiveEventsResourceError()
                }
            }
        }
    }

    private fun handleGetTeamLastFiveEventsResourceSuccess(teamEventList: TeamEventList?) {
        teamEventList?.let {
            teamDetailsAdapter.updateItems(it.results)
        }
    }

    private fun handleGetTeamLastFiveEventsResourceLoading() {
    }

    private fun handleGetTeamLastFiveEventsResourceError() {
    }

    override fun onClickTeamDetails() {
        TODO("Not yet implemented")
    }

    override fun onClickTeamLinks(teamLink: String) {
        val viewIntent = Intent("android.intent.action.VIEW",
            Uri.parse("http://$teamLink/"))
        startActivity(viewIntent)
    }

}