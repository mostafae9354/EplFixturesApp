package com.moessa.eplFixturesApp.module.fixtures_list.domain.repository

import com.moessa.eplFixturesApp.module.fixtures_list.domain.entity.FixtureEntity

interface FixturesRepository {
    suspend fun getFixturesList(): List<FixtureEntity>?
}