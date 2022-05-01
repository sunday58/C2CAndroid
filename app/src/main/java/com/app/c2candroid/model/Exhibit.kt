package com.app.c2candroid.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.c2candroid.restExhibitLoader.localStorage.converters.ExhibitImageConverter
import com.google.gson.annotations.SerializedName

    @Entity(tableName = "exhibit_table")
    data class Exhibit(
        @TypeConverters(ExhibitImageConverter::class)
        @SerializedName("images")
        val images: List<String> = listOf(),
        @SerializedName("title")
        val title: String = ""
    ){
        @PrimaryKey(autoGenerate = false)
        val id: Int = 1
    }

