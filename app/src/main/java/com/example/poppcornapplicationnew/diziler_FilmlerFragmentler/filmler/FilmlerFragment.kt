package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.MovieAdapter
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.example.poppcornapplicationnew.entities.movieResponse.MovieResponse
import com.example.poppcornapplicationnew.retrofit.MovieDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentFilmlerBinding
import retrofit2.Callback
import retrofit2.Response

class FilmlerFragment : Fragment() {

    private lateinit var binding: FragmentFilmlerBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var getMovieDaoInterface: MovieDaoInterface
    private lateinit var list:ArrayList<Movie>

    private var currentpage=1
    private var totalpage=1
    private var isLoading=false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmlerBinding.inflate(inflater, container, false)

        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.layoutManager = GridLayoutManager(requireContext(), 3)


        list= ArrayList()
        adapter= MovieAdapter(requireContext(),list)
        binding.rvMovie.adapter=adapter


        getMovieDaoInterface = ApiUtils.getMovieDaoInterface()
        getFilmler(currentpage)




        binding.rvMovie.addOnScrollListener(object :RecyclerView.OnScrollListener(){
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

    // gptnin kodu aaa
    fun filterMovies(query: String) {
        val filteredList = list.filter { it.title.contains(query, ignoreCase = true) }
        adapter.updateList(filteredList)
    }
}