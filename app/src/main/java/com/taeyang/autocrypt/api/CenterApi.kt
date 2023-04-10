package com.taeyang.autocrypt.api

import com.taeyang.autocrypt.BuildConfig
import com.taeyang.autocrypt.model.CenterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CenterApi {

    @GET("centers")
    fun getCenter(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("serviceKey") serviceKey: String = BuildConfig.API_KEY
    ): Call<CenterData>
}