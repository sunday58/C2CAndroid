package com.app.c2candroid.di

import com.app.c2candroid.apiResponse.ExhibitLoader
import com.app.c2candroid.localStorage.ExhibitDao
import com.app.c2candroid.repository.ExhibitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMainRepository(
        blogDao: ExhibitDao,
        retrofit: ExhibitLoader,
    ): ExhibitRepository {
        return ExhibitRepository(blogDao, retrofit)
    }
}