package com.example.poppcornapplicationnew

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDaoInterface {



    @GET("movie/popular")
    fun getMovie(@Query("api_key") api_key:String = "2c9984115df49ec7e45fb618cdf7f728",
                   @Query("language") language:String="tr-TR",
                   @Query("page") page:Int):Call<MovieResponse>



}