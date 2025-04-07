package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.DiziCategories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.adapter.CategoryAdapters.DiziAdapters.ComedySeriesAdapter
import com.example.poppcornapplicationnew.adapter.TVShowAdapter
import com.example.poppcornapplicationnew.databinding.FragmentDizilerBinding
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShowResponse
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.retrofit.TVShowDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComedySeriesFragment : Fragment() {

    private lateinit var binding: FragmentDizilerBinding
    private lateinit var adapter: ComedySeriesAdapter
    private lateinit var getTVShowDaoInterface: TVShowDaoInterface

    private lateinit var list: ArrayList<TVShow>
    private var currenPage=1
    private var totalpage=1

    private var isLoading=false



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        Log.e("ComedySeries Fragment ","ComedySeries Fragment Açıldı")


        binding = FragmentDizilerBinding.inflate(inflater, container, false)

        binding.rvTVShow.setHasFixedSize(true)
        binding.rvTVShow.layoutManager = GridLayoutManager(requireContext(), 3)

        list=ArrayList()
        adapter= ComedySeriesAdapter(requireContext(),list)

        binding.rvTVShow.adapter = adapter

        getTVShowDaoInterface = ApiUtils.getTVDaoInterface()

        getDiziler(currenPage)

        binding.rvTVShow.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager=recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition()
                val totalItemCount=layoutManager.itemCount


                if (lastVisibleItemPosition== totalItemCount-1 && !isLoading && currenPage<totalpage){
                    currenPage++
                    getDiziler(currenPage)
                }
            }
        })

        return binding.root
    }

    private fun getDiziler(page: Int) {
        isLoading=true
        getTVShowDaoInterface.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if ( response.body() != null) {

                    totalpage=response.body().totalPages
                    val newList=response.body().results


                    var filterliListim=newList.filter {tvShow ->
                        !list.any{it.id==tvShow.id} && tvShow.genreIds.contains(35)
                    }
                    list.addAll(filterliListim)
                }

                isLoading=false
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading=false
            }
        })
    }
}
