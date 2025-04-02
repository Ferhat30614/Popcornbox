package com.example.poppcornapplicationnew.Retrofit

class ApiUtils {

    companion object{



        val BaseUrl="https://api.themoviedb.org/3/"

        fun getMovieDaoInterface(): MovieDaoInterface {

            return RetrofitClient.getClient(BaseUrl).create(MovieDaoInterface::class.java)


        }

        fun getTVDaoInterface(): TVShowDaoInterface {
            return RetrofitClient.getClient(BaseUrl).create(TVShowDaoInterface::class.java)

        }


        fun getTvDetailsDaoInterface(): TvDetailsDaoInterface {
            return RetrofitClient.getClient(BaseUrl).create(TvDetailsDaoInterface::class.java)

        }

        fun getMovieDetailsDaoInterface(): MovieDetailsDaoInterface {
            return RetrofitClient.getClient(BaseUrl).create(MovieDetailsDaoInterface::class.java)

        }



    }
}