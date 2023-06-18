package com.moessa.eplFixturesApp.module.fixtures_list.data.source.remote

import com.moessa.eplFixturesApp.module.fixtures_list.data.model.GetFixturesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface FixturesService {

    @GET("competitions/2021/matches")
    suspend fun getFixturesList(): Response<GetFixturesListResponse>
}