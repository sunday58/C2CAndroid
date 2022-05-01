package com.app.c2candroid.di

import android.content.Context
import androidx.room.Room
import com.app.c2candroid.localStorage.ExhibitDao
import com.app.c2candroid.localStorage.ExhibitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideExhibitDb(@ApplicationContext context: Context): ExhibitDatabase {
        return Room.databaseBuilder(
            context,
            ExhibitDatabase::class.java,
            ExhibitDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideExhibitDao(blogDatabase: ExhibitDatabase): ExhibitDao {
        return blogDatabase.exhibitDao()
    }

}