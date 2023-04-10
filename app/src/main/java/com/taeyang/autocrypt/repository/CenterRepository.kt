package com.taeyang.autocrypt.repository

import com.taeyang.autocrypt.di.RetrofitInstance
import com.taeyang.autocrypt.model.CenterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CenterRepository @Inject constructor(

){

    suspend fun getBestAPI(
        page: Int,
        perPage: Int
    ): CenterData = withContext(Dispatchers.IO){
        RetrofitInstance.api.getCenter(page, perPage)
            .awaitResponse()
            .bodyOrThrow()
    }
}

fun <T> Response<T>.bodyOrThrow(): T = if(isSuccessful) body()!! else throw HttpException(this)