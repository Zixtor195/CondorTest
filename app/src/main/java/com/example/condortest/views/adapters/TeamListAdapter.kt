package com.example.condortest.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.condortest.databinding.ItemlistTeamBinding
import com.example.condortest.models.TeamInfo

class TeamListAdapter(private val listener: TeamListener) : RecyclerView.Adapter<TeamListAdapter.ViewHolder>() {

    private var items: List<TeamInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = items[position]
        holder.bind(team)
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(teams: List<TeamInfo>) {
        this.items = teams
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemlistTeamBinding, private val listener: TeamListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamInfo) {
            binding.textViewStadium.text = team.strStadium
            binding.textViewTeamName.text = team.strTeam
            Glide.with(itemView.context).load(team.strTeamBadge).into(binding.imageViewTeamBadge)

            binding.cardViewTeam.setOnClickListener {
                listener.onClickTeam(team)
            }
        }
    }

    interface TeamListener {
        fun onClickTeam(team: TeamInfo)
    }

}