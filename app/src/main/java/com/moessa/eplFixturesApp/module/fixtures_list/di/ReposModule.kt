package com.moessa.eplFixturesApp.module.fixtures_list.di

import com.moessa.eplFixturesApp.module.fixtures_list.data.repository.FixturesRepositoryImpl
import com.moessa.eplFixturesApp.module.fixtures_list.data.source.remote.FixturesService
import com.moessa.eplFixturesApp.module.fixtures_list.domain.repository.FixturesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {

    companion object {
        @Provides
        @ViewModelScoped
        fun provideFixturesService(retrofit: Retrofit): FixturesService =
            retrofit.create(FixturesService::class.java)
    }

    @Binds
    @ViewModelScoped
    abstract fun bindDocsRepository(impl: FixturesRepositoryImpl): FixturesRepository
}