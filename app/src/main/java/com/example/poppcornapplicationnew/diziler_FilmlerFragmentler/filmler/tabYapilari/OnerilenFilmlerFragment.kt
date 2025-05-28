package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.OnerilenFilmlerAdapter
import com.example.poppcornapplicationnew.databinding.FragmentOnerilenFilmlerBinding
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.example.poppcornapplicationnew.entities.movieResponse.MovieResponse
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.retrofit.MovieDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnerilenFilmlerFragment : Fragment() {

    private lateinit var binding: FragmentOnerilenFilmlerBinding
    private lateinit var adapter: OnerilenFilmlerAdapter
    private lateinit var getMovieDaoInterface: MovieDaoInterface
    private lateinit var list: ArrayList<Movie>

    private var currentPage = 1
    private var totalPage = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("ÖnerilenFilmler Fragment", "ÖnerilenFilmler Fragment Açıldı")

        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("fragment onerilen filmler", "Movie argümanı eksik")
        }

        binding = FragmentOnerilenFilmlerBinding.inflate(inflater, container, false)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = OnerilenFilmlerAdapter(this@OnerilenFilmlerFragment, list)

        binding.rv.adapter = adapter

        getMovieDaoInterface = ApiUtils.getMovieDaoInterface()
        getFilmler(currentPage)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && currentPage < totalPage && !isLoading) {
                    currentPage++
                    getFilmler(currentPage)
                }
            }
        })

        return binding.root
    }

    private fun getFilmler(page: Int) {
        isLoading = true
        getMovieDaoInterface.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let { responseBody ->
                    totalPage = responseBody.total_pages

                    val newList = responseBody.results.filterNot { newMovie ->
                        list.any { it.id == newMovie.id }
                    }

                    list.addAll(newList)
                    adapter.notifyDataSetChanged()
                }
                isLoading = false
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
            }
        })
    }
}
