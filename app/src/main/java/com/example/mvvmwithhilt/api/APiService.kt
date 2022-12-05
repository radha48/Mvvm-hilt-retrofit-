package com.example.mvvmwithhilt.api

import com.example.mvvmwithhilt.models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APiService {

    @GET("popular")
    suspend fun getMovie(@Query ("api_key") noteRequest: String) : Response<Movie>
}