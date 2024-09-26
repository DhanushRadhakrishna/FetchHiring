package com.example.fetchhiring.Api

import com.example.fetchhiring.Model.Fetch
import retrofit2.http.GET

//https://fetch-hiring.s3.amazonaws.com/
interface FetchApi {

    @GET("hiring.json")
    suspend fun getData() : List<Fetch>
}