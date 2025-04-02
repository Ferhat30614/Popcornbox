package com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler.TabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.Adapter.MovieAdapter
import com.example.poppcornapplicationnew.Retrofit.ApiUtils
import com.example.poppcornapplicationnew.Entities.MovieResponse.Movie
import com.example.poppcornapplicationnew.Entities.MovieResponse.MovieResponse
import com.example.poppcornapplicationnew.Retrofit.MovieDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentBenzerFilmlerBinding
import retrofit2.Callback
import retrofit2.Response

class FragmentBenzerFilmler : Fragment() {
    private lateinit var binding: FragmentBenzerFilmlerBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var gmdi: MovieDaoInterface
    private lateinit var list: ArrayList<Movie>

    private var currentpage = 1
    private var totalpage = 1
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBenzerFilmlerBinding.inflate(inflater, container, false)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = MovieAdapter(requireContext(), list)
        binding.rv.adapter = adapter

        gmdi = ApiUtils.getMovieDaoInterface()
        getFilmler(currentpage)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutmanager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutmanager.findLastVisibleItemPosition()
                val totalItemcount = layoutmanager.itemCount

                if (lastVisibleItemPosition == totalItemcount - 1 && currentpage < totalpage && !isLoading) {
                    currentpage++
                    getFilmler(currentpage)
                }
            }
        })

        return binding.root
    }
    private fun getFilmler(page: Int, yukaridenEkleme: Boolean = false) {
        isLoading = true
        gmdi.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.body() != null) {

                    totalpage = response.body().total_pages // total page yazdım

                    val yenilist = response.body()?.results ?: emptyList()

                    // Gelen listeyi mevcut listede olmayan filmlere göre filtrele
                    val newList = yenilist.filter { movie ->
                        !list.any { it.id == movie.id }
                    }

                    // Filtrelenmiş listeden yalnızca kategori ID'si 28 olanları seç
                    val actionMovies = newList.filter { movie ->
                        movie.genreIds.contains(27)
                    }

                    // Aksiyon filmlerini listeye ekle
                    list.addAll(actionMovies)

                    isLoading = false
                    Log.e("Liste boyutu", list.size.toString())

                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
            }
        })
    }


}