package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.OnerilenFilmlerAdapter
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.example.poppcornapplicationnew.entities.movieResponse.MovieResponse
import com.example.poppcornapplicationnew.retrofit.MovieDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentOnerilenFilmlerBinding
import retrofit2.Callback
import retrofit2.Response

class OnerilenFilmlerFragment : Fragment() {


    private lateinit var binding: FragmentOnerilenFilmlerBinding
    private lateinit var adapter: OnerilenFilmlerAdapter
    private lateinit var getMovieDaoInterface: MovieDaoInterface
    private lateinit var list:ArrayList<Movie>

    private var currentpage=1
    private var totalpage=1
    private var isLoading=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Log.e("ÖnerilenFilmler Fragment ","ÖnerilenFilmler Fragment Açıldı")


        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("fragment onerilen filmler", "Movie argümanı eksik")
        }

        binding = FragmentOnerilenFilmlerBinding.inflate(inflater,container,false)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)



        list= ArrayList()
        adapter= OnerilenFilmlerAdapter(requireContext(),list)
        binding.rv.adapter=adapter



        getMovieDaoInterface = ApiUtils.getMovieDaoInterface()
        getFilmler(currentpage)



        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                val layoutmanager=recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition=layoutmanager.findLastVisibleItemPosition()
                val totalItemcount=layoutmanager.itemCount

                if(lastVisibleItemPosition == totalItemcount-1 && currentpage<totalpage&& !isLoading){
                    currentpage++
                    getFilmler(currentpage)
                }
            }
        })
             return binding.root
    }


    private fun getFilmler(page: Int, yukaridenEkleme:Boolean=false) {
        isLoading=true
        getMovieDaoInterface.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.body() != null) {


                    totalpage=response.body().total_pages  //total page yazdım

                    val yenilist=response.body().results

                    val newList=yenilist.filter { movie: Movie ->
                        !list.any { it.id==movie.id }
                    }

                    list.addAll(newList)

                    isLoading=false

                    adapter.notifyDataSetChanged()

                }
            }
            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading=false
            }
        })
    }
}