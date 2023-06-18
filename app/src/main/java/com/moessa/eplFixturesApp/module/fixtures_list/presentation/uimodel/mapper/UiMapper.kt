package com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel.mapper

import com.moessa.eplFixturesApp.module.fixtures_list.domain.entity.FixtureEntity
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel.FixtureUiModel
import java.text.SimpleDateFormat
import java.util.*

fun FixtureEntity.toUiModel() = FixtureUiModel(
    id = id,
    homeTeamName = homeTeamName,
    homeTeamLogo = homeTeamLogo,
    awayTeamName = awayTeamName,
    awayTeamLogo = awayTeamLogo,
    scoreOrDate = if (!score.contains("null")) score else {
        SimpleDateFormat("hh:mm").format(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(
                utcDate
            ) ?: Date()
        )
    }
)