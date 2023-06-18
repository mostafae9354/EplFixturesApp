package com.moessa.eplFixturesApp.module.fixtures_list.data.repository

import com.moessa.eplFixturesApp.module.fixtures_list.data.model.mapper.toEntity
import com.moessa.eplFixturesApp.module.fixtures_list.data.source.remote.FixturesService
import com.moessa.eplFixturesApp.module.fixtures_list.domain.entity.FixtureEntity
import com.moessa.eplFixturesApp.module.fixtures_list.domain.repository.FixturesRepository
import javax.inject.Inject

class FixturesRepositoryImpl @Inject constructor(
    private val service: FixturesService
) : FixturesRepository {
    override suspend fun getFixturesList(): List<FixtureEntity>? {
        return service.getFixturesList().body()?.matches?.map {
            it.toEntity()
        }
    }
}