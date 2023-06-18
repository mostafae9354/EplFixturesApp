package com.moessa.eplFixturesApp.module.fixtures_list.data.model.mapper

import com.moessa.eplFixturesApp.module.fixtures_list.data.model.Match
import com.moessa.eplFixturesApp.module.fixtures_list.domain.entity.FixtureEntity

fun Match.toEntity() = FixtureEntity(
    id = id,
    homeTeamName = homeTeam.name,
    homeTeamLogo = homeTeam.crest,
    awayTeamName = awayTeam.name,
    awayTeamLogo = awayTeam.crest,
    score = "${score.fullTime.homeTeam} - ${score.fullTime.awayTeam}",
    utcDate = utcDate
)