package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.databinding.FragmentFilmlerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmlerFragment : Fragment() {

    private lateinit var binding: FragmentFilmlerBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var list: ArrayList<Movie>
    private lateinit var gmdi: MovieDaoInterface

    private var currentPage = 1
    private var totalPages = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmlerBinding.inflate(inflater, container, false)

        // RecyclerView ve adapter ayarları
        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = MovieAdapter(requireContext(), list)
        binding.rvMovie.adapter = adapter

        // API arayüzü
        gmdi = ApiUtils.getMovieDaoInterface()

        // İlk sayfayı yükle
        getFilmler(currentPage)

        // Sonsuz kaydırma dinleyicisi ekle
        binding.rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                // Aşağı kaydırma: Yeni sayfa yükleme
                if (lastVisibleItemPosition == totalItemCount - 1 && currentPage < totalPages) {
                    currentPage++
                    getFilmler(currentPage)
                }

                // Yukarı kaydırma: Önceki sayfayı yükleme
                if (firstVisibleItemPosition == 0 && currentPage > 1) {
                    currentPage--
                    getFilmler(currentPage, isAppendToTop = true)
                }
            }
        })

        return binding.root
    }

    private fun getFilmler(page: Int, isAppendToTop: Boolean = false) {
        gmdi.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val movieResponse = response.body()!!
                    totalPages = movieResponse.total_pages // Toplam sayfa sayısını güncelle

                    // Yeni gelen filmleri kontrol et
                    val newMovies = movieResponse.results.filter { movie ->
                        !list.any { it.id == movie.id } // Yeni gelen film listede yoksa ekle
                    }

                    if (isAppendToTop) {
                        // Yukarıya ekle
                        list.addAll(0, newMovies)
                    } else {
                        // Aşağıya ekle
                        list.addAll(newMovies)
                    }
                    adapter.notifyDataSetChanged() // Listeyi güncelle
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
