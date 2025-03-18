package com.example.poppcornapplicationnew

import com.example.poppcornapplicationnew.Entities.TVShowResponse.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowDaoInterface {


    @GET("tv/popular")
    fun getTvShow(@Query("api_key") api_key:String = "2c9984115df49ec7e45fb618cdf7f728",
                 @Query("language") language:String="tr-TR",
                 @Query("page") page:Int): Call<TVShowResponse>



}