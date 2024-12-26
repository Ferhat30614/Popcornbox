package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.poppcornapplicationnew.databinding.FragmentTvshowDetailBinding
import com.squareup.picasso.Picasso

class TVShowDetailFragment : Fragment() {

    private lateinit var binding: FragmentTvshowDetailBinding
    private val args: TVShowDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowDetailBinding.inflate(inflater, container, false)

        // Navigation Component ile gelen veriyi al

       // var bundle=TVShowDetailFragmentArgs by navArgs()


        var tvShow=args.tvShow





        tvShow?.let {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .into(binding.posterImage)

            binding.name.text = it.name
            binding.releaseDate.text = "First Air Date: ${it.firstAirDate}"
            binding.popularity.text = "Popularity: ${it.popularity}"
            binding.voteAverage.text = "Vote Average: ${it.voteAverage}"
            binding.overview.text = it.overview
        }

        return binding.root
    }
}
