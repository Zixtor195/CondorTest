package com.example.condortest.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.condortest.databinding.ItemlistTeamDetailsBinding
import com.example.condortest.models.TeamEvent

class TeamLinksAdapter(private val listener: TeamLinksListener) : RecyclerView.Adapter<TeamLinksAdapter.ViewHolder>() {

    private var items: List<String> = emptyList()

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
    fun updateItems(teamLinks: List<String>) {
        this.items = teamLinks
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemlistTeamDetailsBinding, private val listener: TeamLinksListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamLink: String) {
            binding.textViewDetailName.text = teamLink

            binding.cardViewTeam.setOnClickListener {
                listener.onClickTeamLinks(teamLink)
            }
        }
    }

    interface TeamLinksListener {
        fun onClickTeamLinks(teamLink: String)
    }

}