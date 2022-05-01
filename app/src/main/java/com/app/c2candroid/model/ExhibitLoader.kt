package com.app.c2candroid.model


import com.app.c2candroid.utils.Constants.EXHIBIT_LIST
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ExhibitLoader {

    @GET(EXHIBIT_LIST)
    suspend fun getExhibitList(): ApiResponse<ArrayList<Exhibit>>
}