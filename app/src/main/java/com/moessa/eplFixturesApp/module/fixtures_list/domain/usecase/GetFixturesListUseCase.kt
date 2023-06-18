package com.moessa.eplFixturesApp.module.fixtures_list.domain.usecase

import com.moessa.eplFixturesApp.module.fixtures_list.domain.entity.FixtureEntity
import com.moessa.eplFixturesApp.module.fixtures_list.domain.repository.FixturesRepository
import javax.inject.Inject

class GetFixturesListUseCase @Inject constructor(private val repository: FixturesRepository) {
    suspend operator fun invoke(): List<FixtureEntity>? {
        return repository.getFixturesList()
    }
}