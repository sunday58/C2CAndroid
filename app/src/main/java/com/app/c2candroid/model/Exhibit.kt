package com.app.c2candroid.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.c2candroid.restExhibitLoader.localStorage.converters.ExhibitImageConverter
import com.google.gson.annotations.SerializedName

    @Entity(tableName = "exhibit_table")
    data class Exhibit(
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        @TypeConverters(ExhibitImageConverter::class)
        @ColumnInfo(name = "images")
        @SerializedName("images")
        val images: List<String> = listOf(),
        @ColumnInfo(name = "title")
        @SerializedName("title")
        val title: String = ""
    )

