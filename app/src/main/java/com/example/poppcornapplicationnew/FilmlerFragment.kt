package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poppcornapplicationnew.databinding.FragmentFilmlerBinding

class FilmlerFragment : Fragment() {

    private lateinit var binding: FragmentFilmlerBinding
    private lateinit var adapter:MovieAdapter
    private lateinit var list:ArrayList<Movie>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        binding=FragmentFilmlerBinding.inflate(inflater,container,false)

        binding.rvmovie.setHasFixedSize(true)
        binding.rvmovie.layoutManager=GridLayoutManager(requireContext(),3)

        list= ArrayList()


        val movie1 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "interstellar", releaseDate = "2024-12-25", title = "Interstellar", video = false, voteAverage = 7.8, voteCount = 1111)
        val movie2 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "birzamanlaranadoluda", releaseDate = "2024-12-25", title = "Bir zamanlar", video = false, voteAverage = 7.8, voteCount = 1111)
        val movie3 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "inception", releaseDate = "2024-12-25", title = "Inception", video = false, voteAverage = 7.8, voteCount = 1111)

        val movie4 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "thehatefuleight", releaseDate = "2024-12-25", title = "thehatefuleight", video = false, voteAverage = 7.8, voteCount = 1111)
        val movie5 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "django", releaseDate = "2024-12-25", title = "django", video = false, voteAverage = 7.8, voteCount = 1111)
        val movie6 = Movie(adult = false, backdropPath = "/path/to/backdrop.jpg", genreIds = arrayListOf(28, 12, 35), id = 123456, originalLanguage = "en", originalTitle = "Red One", overview = "A Christmas adventure with action and comedy.", popularity = 1200.5, posterPath = "thepianist", releaseDate = "2024-12-25", title = "thepianist", video = false, voteAverage = 7.8, voteCount = 1111)

        list.add(movie1)
        list.add(movie2)
        list.add(movie3)
        list.add(movie4)
        list.add(movie5)
        list.add(movie6)

        adapter=MovieAdapter(requireContext(),list)

        binding.rvmovie.adapter=adapter


        return binding.root
    }


}