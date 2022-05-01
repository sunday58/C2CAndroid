package com.app.c2candroid.apiResponse


import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExhibitLoader {

    @GET("group")
    suspend fun getExhibitList(): ApiResponse<Exhibit>
}