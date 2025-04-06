package com.example.poppcornapplicationnew.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        fun getClient(baseurl:String):Retrofit{

            return Retrofit.Builder().
            baseUrl(baseurl).
            addConverterFactory(GsonConverterFactory.create()).
            build()
        }

    }


}