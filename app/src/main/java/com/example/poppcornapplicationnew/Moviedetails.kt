package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.poppcornapplicationnew.databinding.FragmentMoviedetailsBinding
import com.squareup.picasso.Picasso

class Moviedetails : Fragment() {

    private lateinit var binding: FragmentMoviedetailsBinding
    private val args: MoviedetailsArgs by navArgs() // Hatalı olan sınıf adı düzeltildi



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviedetailsBinding.inflate(inflater, container, false)

        val movie = args.movieShow // NavArgs ile gelen veriyi al

        movie?.let {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .into(binding.posterImage)

            binding.name.text = it.title
            binding.releaseDate.text = "Release Date: ${it.releaseDate}"
            binding.popularity.text = "Popularity: ${it.popularity}"
            binding.voteAverage.text = "Vote Average: ${it.voteAverage}"
            binding.overview.text = it.overview
        }

        return binding.root
    }
}
