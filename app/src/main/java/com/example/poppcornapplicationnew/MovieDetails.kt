package com.example.poppcornapplicationnew

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.poppcornapplicationnew.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var mddi: MovieDetailsDaoInterface
    private lateinit var yeniFilm: MediaDetails


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMovieDetailsBinding.inflate(inflater,container,false)

        mddi=ApiUtils.getMovieDetailsDaoInterface()


        val bundle:MovieDetailsArgs by navArgs()
        val Movie=bundle.myMovie
        getFilmlerDetails(Movie.id)


        if (yeniFilm.backdrop_path!=null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${yeniFilm.backdrop_path}")
                .placeholder(R.drawable.yukleniyo)
                .error(R.drawable.interstellar)
                .into(binding.ivMoviePoster)
        }



        return binding.root
    }

    private fun getFilmlerDetails(id:Int){


        mddi.getMovieDetails(movieId = id).enqueue(object:Callback<MediaDetails>{
            override fun onResponse(call: Call<MediaDetails>?, response: Response<MediaDetails>) {

                if (response.body() != null) {
                     yeniFilm=response.body()



                }


            }

            override fun onFailure(call: Call<MediaDetails>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })


    }


}