package com.moessa.eplFixturesApp.module.fixtures_list.data.model

data class GetFixturesListResponse(
    val competition: Competition,
    val count: Int,
    val matches: List<Match>
)

data class Competition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)

data class Match(
    val awayTeam: AwayTeam,
    val group: Any,
    val homeTeam: HomeTeam,
    val id: Int,
    val lastUpdated: String,
    val matchday: Int,
    val odds: Odds,
    val referees: List<Any>,
    val score: Score,
    val season: Season,
    val stage: String,
    val status: String,
    val utcDate: String
)

data class Area(
    val id: Int,
    val name: String
)

data class AwayTeam(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String
)

data class HomeTeam(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String
)

data class Odds(
    val msg: String
)

data class Score(
    val duration: String,
    val extraTime: ExtraTime,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val penalties: Penalties,
    val winner: Any
)

data class Season(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String
)

data class ExtraTime(
    val awayTeam: Any,
    val homeTeam: Any
)

data class FullTime(
    val awayTeam: Any,
    val homeTeam: Any
)

data class HalfTime(
    val awayTeam: Any,
    val homeTeam: Any
)

data class Penalties(
    val awayTeam: Any,
    val homeTeam: Any
)