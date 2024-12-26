package com.example.poppcornapplicationnew

class ApiUtils {

    companion object{



        val BaseUrl="https://api.themoviedb.org/3/"

        fun getMovieDaoInterface():MovieDaoInterface{

            return RetrofitClient.getClient(BaseUrl).create(MovieDaoInterface::class.java)


        }

        fun getTVDaoInterface():TVShowDaoInterface{
            return RetrofitClient.getClient(BaseUrl).create(TVShowDaoInterface::class.java)

        }





    }
}