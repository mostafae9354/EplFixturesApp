package com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel

data class FixtureUiModel(
    val id: Int,
    val homeTeamName: String,
    val homeTeamLogo: String,
    val awayTeamName: String,
    val awayTeamLogo: String,
    val scoreOrDate: String
)