package com.moessa.eplFixturesApp.module.fixtures_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moessa.eplFixturesApp.R
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel.FixtureUiModel
import com.squareup.picasso.Picasso

class FixturesAdapter : ListAdapter<FixtureUiModel, FixturesAdapter.DataViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<FixtureUiModel>() {
        override fun areItemsTheSame(oldItem: FixtureUiModel, newItem: FixtureUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FixtureUiModel, newItem: FixtureUiModel): Boolean {
            return oldItem.homeTeamName == newItem.homeTeamName &&
                    oldItem.awayTeamName == newItem.awayTeamName &&
                    oldItem.scoreOrDate == newItem.scoreOrDate
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvHomeTeam: AppCompatTextView = itemView.findViewById(R.id.tv_home_team)
        private val ivHomeTeam: AppCompatImageView = itemView.findViewById(R.id.iv_home_team)
        private val tvAwayTeam: AppCompatTextView = itemView.findViewById(R.id.tv_away_team)
        private val ivAwayTeam: AppCompatImageView = itemView.findViewById(R.id.iv_away_team)
        private val tvScore: AppCompatTextView = itemView.findViewById(R.id.tv_score_or_date)

        fun bind(fixtureUiModel: FixtureUiModel) {
            tvHomeTeam.text = fixtureUiModel.homeTeamName
            tvAwayTeam.text = fixtureUiModel.awayTeamName
            tvScore.text = fixtureUiModel.scoreOrDate

            Picasso.get().load(fixtureUiModel.homeTeamLogo).into(ivHomeTeam)
            Picasso.get().load(fixtureUiModel.awayTeamLogo).into(ivAwayTeam)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fixture, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(getItem(position))
}