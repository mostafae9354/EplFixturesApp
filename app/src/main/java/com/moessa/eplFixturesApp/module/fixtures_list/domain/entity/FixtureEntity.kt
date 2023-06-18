package com.moessa.eplFixturesApp.module.fixtures_list.domain.entity

data class FixtureEntity(
    val id: Int,
    val homeTeamName: String,
    val homeTeamLogo: String,
    val awayTeamName: String,
    val awayTeamLogo: String,
    val score: String,
    val utcDate: String
)