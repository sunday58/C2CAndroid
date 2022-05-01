package com.app.c2candroid.di

import com.app.c2candroid.model.ExhibitLoader
import com.app.c2candroid.restExhibitLoader.localStorage.ExhibitDao
import com.app.c2candroid.restExhibitLoader.repository.ExhibitRepository
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