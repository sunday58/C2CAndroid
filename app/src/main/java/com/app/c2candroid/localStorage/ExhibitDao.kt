package com.app.c2candroid.localStorage

import androidx.room.*
import com.app.c2candroid.apiResponse.Exhibit

@Dao
interface ExhibitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: List<Exhibit>)

    @Query("SELECT * FROM exhibit_table")
    suspend fun get(): List<Exhibit>


}