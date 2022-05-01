package com.app.c2candroid.model


import com.google.gson.annotations.SerializedName

class ExibitEntity : ArrayList<ExibitEntity.ExibitEntityItem>(){

    data class ExibitEntityItem(
        @SerializedName("images")
        val images: List<String> = listOf(),
        @SerializedName("title")
        val title: String = ""
    )

}