package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.BenzerFilmlerAdapter
import com.example.poppcornapplicationnew.adapter.MovieAdapter
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.example.poppcornapplicationnew.entities.movieResponse.MovieResponse
import com.example.poppcornapplicationnew.retrofit.MovieDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentBenzerFilmlerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BenzerFilmlerFragment : Fragment() {

    interface OnBenzerClickListener {
        fun onBenzerClick(movie: Movie)
    }

    private var clickListener: OnBenzerClickListener? = null
    fun setOnClickListener(listener: OnBenzerClickListener) {
        clickListener = listener
    }

    private lateinit var binding: FragmentBenzerFilmlerBinding
    private lateinit var adapter: BenzerFilmlerAdapter
    private lateinit var getMovieDaoInterface: MovieDaoInterface
    private lateinit var list: ArrayList<Movie>
    private var currentPage = 1
    private var totalPage = 1
    private var isLoading = false
    private lateinit var genreIdList: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("BenzerFilmlerFragment", "Movie argümanı null geldi.")
        } else {
            genreIdList = movie.genreIds.toList()
        }

        binding = FragmentBenzerFilmlerBinding.inflate(inflater, container, false)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = BenzerFilmlerAdapter(this@BenzerFilmlerFragment, list)
        binding.rv.adapter = adapter

        getMovieDaoInterface = ApiUtils.getMovieDaoInterface()
        getMovies(currentPage)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val total = layoutManager.itemCount

                if (lastVisible == total - 1 && !isLoading && currentPage < totalPage) {
                    currentPage++
                    getMovies(currentPage)
                }
            }
        })

        return binding.root
    }

    private fun getMovies(page: Int) {
        isLoading = true
        getMovieDaoInterface.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    totalPage = it.total_pages
                    val filtered = it.results.filterNot { new -> list.any { old -> old.id == new.id } }
                        .filter { it.genreIds.any { id -> id in genreIdList } }

                    list.addAll(filtered)
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
