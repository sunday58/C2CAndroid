package com.app.c2candroid.apiResponse


import com.google.gson.annotations.SerializedName

class Exhibit : ArrayList<Exhibit.ExhibitItem>(){

    data class ExhibitItem(
        @SerializedName("images")
        val images: List<String> = listOf(),
        @SerializedName("title")
        val title: String = ""
    )

}