package com.example.poppcornapplicationnew

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsDaoInterface {


    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Query("movie_id") movieId: Int,                      // Film ID'si (path parametresi)
        @Query("api_key") apiKey: String = "2c9984115df49ec7e45fb618cdf7f728", // API anahtarı
        @Query("language") language: String = "tr-TR"        // Yanıt dili
    ): Call<MediaDetails> // Film detayları için model




}