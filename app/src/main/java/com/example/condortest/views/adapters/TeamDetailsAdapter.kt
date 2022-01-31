package com.example.condortest.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.condortest.databinding.ItemlistTeamDetailsBinding
import com.example.condortest.models.TeamEvent

class TeamDetailsAdapter(private val listener: TeamDetailsListener) :
    RecyclerView.Adapter<TeamDetailsAdapter.ViewHolder>() {

    private var items: List<TeamEvent> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistTeamDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = items[position]
        holder.bind(team)
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(teams: List<TeamEvent>) {
        this.items = teams
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemlistTeamDetailsBinding, private val listener: TeamDetailsListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamEvent: TeamEvent) {
            binding.textViewDetailName.text = teamEvent.strFilename

            binding.cardViewTeam.setOnClickListener {
                listener.onClickTeamDetails()
            }
        }
    }

    interface TeamDetailsListener {
        fun onClickTeamDetails()
    }

}