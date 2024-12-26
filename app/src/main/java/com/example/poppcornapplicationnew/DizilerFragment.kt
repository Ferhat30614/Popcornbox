package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.databinding.FragmentDizilerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DizilerFragment : Fragment() {

    private lateinit var binding: FragmentDizilerBinding
    private lateinit var adapter: TVShowAdapter
    private lateinit var tvShowList: ArrayList<TVShow>
    private lateinit var gmdi: TVShowDaoInterface

    private var currentPage = 1
    private var totalPages = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDizilerBinding.inflate(inflater, container, false)

        // RecyclerView ve Adapter ayarları
        binding.rvTVShow.setHasFixedSize(true)
        binding.rvTVShow.layoutManager = GridLayoutManager(requireContext(), 3)

        tvShowList = ArrayList()
        adapter = TVShowAdapter(requireContext(), tvShowList)
        binding.rvTVShow.adapter = adapter

        // API arayüzü
        gmdi = ApiUtils.getTVDaoInterface()

        // İlk sayfayı yükle
        getDiziler(currentPage)

        // Sonsuz kaydırma dinleyicisi ekle
        binding.rvTVShow.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                // Aşağı kaydırma: Yeni sayfa yükleme
                if (lastVisibleItemPosition == totalItemCount - 1 && currentPage < totalPages) {
                    currentPage++
                    getDiziler(currentPage)
                }

                // Yukarı kaydırma: Önceki sayfayı yükleme
                if (firstVisibleItemPosition == 0 && currentPage > 1) {
                    currentPage--
                    getDiziler(currentPage, isAppendToTop = true)
                }
            }
        })

        return binding.root
    }

    private fun getDiziler(page: Int, isAppendToTop: Boolean = false) {
        gmdi.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val tvShowResponse = response.body()!!
                    totalPages = tvShowResponse.totalPages // Toplam sayfa sayısını güncelle

                    // Yeni gelen dizileri kontrol et ve sadece yeni olanları ekle
                    val newTVShows = tvShowResponse.results.filter { tvShow ->
                        tvShowList.none { it.id == tvShow.id } // Zaten ekli olanları çıkar
                    }

                    if (isAppendToTop) {
                        // Yukarıya ekle
                        tvShowList.addAll(0, newTVShows)
                    } else {
                        // Aşağıya ekle
                        tvShowList.addAll(newTVShows)
                    }
                    adapter.notifyDataSetChanged() // Listeyi güncelle
                    //deneme
                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
