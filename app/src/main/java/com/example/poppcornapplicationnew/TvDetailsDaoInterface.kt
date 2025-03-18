package com.example.poppcornapplicationnew

import com.example.poppcornapplicationnew.Entities.MediaDetailResponse.MediaDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvDetailsDaoInterface {

    @GET("tv/{tv_id}")
    fun getTvShowDetails(
        @Path("tv_id") tvId: Int,                        // Dizi ID'si (path parametresi)
        @Query("api_key") apiKey: String = "2c9984115df49ec7e45fb618cdf7f728", // API anahtarı
        @Query("language") language: String = "tr-TR"    // Yanıt dili
    ): Call<MediaDetailResponse> // MediaDetail veya TVShowResponse modeline eşlenebilir

}




