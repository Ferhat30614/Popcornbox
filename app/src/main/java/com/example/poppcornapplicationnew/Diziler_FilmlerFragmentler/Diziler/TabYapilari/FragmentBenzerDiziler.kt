package com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Diziler.TabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.Adapter.TVShowAdapter
import com.example.poppcornapplicationnew.ApiUtils
import com.example.poppcornapplicationnew.Entities.TVShowResponse.TVShow
import com.example.poppcornapplicationnew.Entities.TVShowResponse.TVShowResponse
import com.example.poppcornapplicationnew.TVShowDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentBenzerDizilerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentBenzerDiziler : Fragment() {
    private lateinit var binding: FragmentBenzerDizilerBinding
    private lateinit var adapter: TVShowAdapter
    private lateinit var gtsi: TVShowDaoInterface

    private lateinit var list: ArrayList<TVShow>
    private var currenPage=1
    private var totalpage=1

    private var isLoading=false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBenzerDizilerBinding.inflate(inflater, container, false)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list=ArrayList()
        adapter= TVShowAdapter(requireContext(),list)

        binding.rv.adapter = adapter

        gtsi = ApiUtils.getTVDaoInterface()

        getDiziler(currenPage)


        binding.rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){

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
        gtsi.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if ( response.body() != null) {

                    totalpage=response.body().totalPages
                    val newList=response.body()?.results ?: emptyList()


                    Log.e("liste boyutu",newList.size.toString())


                    var filterliListim=newList.filter {tvShow ->
                        !list.any{it.id==tvShow.id}
                    }
                    Log.e("liste boyutu",list.size.toString())

                    var actionseris=filterliListim.filter { tvShow ->

                        tvShow.genreIds.contains(10759)
                    }

                    list.addAll(actionseris)

                }
                Log.e("liste boyutu",list.size.toString())

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