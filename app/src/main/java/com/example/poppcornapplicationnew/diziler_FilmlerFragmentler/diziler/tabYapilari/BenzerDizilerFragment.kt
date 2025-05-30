package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.BenzerDizilerAdapter
import com.example.poppcornapplicationnew.databinding.FragmentBenzerDizilerBinding
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShowResponse
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.retrofit.TVShowDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BenzerDizilerFragment : Fragment() {

    private lateinit var binding: FragmentBenzerDizilerBinding
    private lateinit var adapter: BenzerDizilerAdapter
    private lateinit var getTVShowDaoInterface: TVShowDaoInterface
    private lateinit var list: ArrayList<TVShow>
    private var currenPage = 1
    private var totalpage = 1
    private lateinit var getTvShowIds: List<Int>
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("BenzerDiziler Fragment", "BenzerDiziler Fragment Açıldı")

        val tvshow = arguments?.getParcelable<TVShow>("dizi")
        if (tvshow == null) {
            Log.e("tvshowdan aldığın Tvshow nesnesi NUll", "tvshowdan aldığın Tvshow nesnesi NUll")
        } else {
            getTvShowIds = tvshow.genreIds.toList()
        }

        binding = FragmentBenzerDizilerBinding.inflate(inflater, container, false)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = BenzerDizilerAdapter(this@BenzerDizilerFragment, list)
        binding.rv.adapter = adapter

        getTVShowDaoInterface = ApiUtils.getTVDaoInterface()
        getDiziler(currenPage)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && !isLoading && currenPage < totalpage) {
                    currenPage++
                    getDiziler(currenPage)
                }
            }
        })

        return binding.root
    }

    private fun getDiziler(page: Int) {
        isLoading = true
        getTVShowDaoInterface.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                response.body()?.let {
                    totalpage = it.totalPages
                    val newList = it.results.filterNot { new -> list.any { old -> old.id == new.id } }
                    val filtered = newList.filter { tvShow -> tvShow.genreIds.any { it in getTvShowIds } }
                    list.addAll(filtered)
                    adapter.notifyDataSetChanged()
                }
                isLoading = false
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
            }
        })
    }
}
