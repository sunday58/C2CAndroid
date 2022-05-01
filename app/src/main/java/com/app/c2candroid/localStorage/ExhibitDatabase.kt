package com.app.c2candroid.localStorage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.c2candroid.apiResponse.Exhibit
import com.app.c2candroid.localStorage.converters.ExhibitImageConverter

@Database(
    entities = [Exhibit::class],
    version = 1
)
@TypeConverters(ExhibitImageConverter::class)
abstract class ExhibitDatabase: RoomDatabase() {

    abstract fun exhibitDao(): ExhibitDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }

}