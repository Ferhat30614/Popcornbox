package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poppcornapplicationnew.databinding.FragmentFilmlerBinding
import retrofit2.Callback
import retrofit2.Response

class FilmlerFragment : Fragment() {

    private lateinit var binding: FragmentFilmlerBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var gmdi: MovieDaoInterface


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmlerBinding.inflate(inflater, container, false)

        binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.layoutManager = GridLayoutManager(requireContext(), 3)


        gmdi = ApiUtils.getMovieDaoInterface()
        getFilmler(1)



        return binding.root
    }

    private fun getFilmler(page: Int) {
        gmdi.getMovie(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: retrofit2.Call<MovieResponse> ,response: Response<MovieResponse>) {
                if (response.body() != null) {

                    val list=response.body().results
                    adapter=MovieAdapter(requireContext(),list)
                    binding.rvMovie.adapter=adapter



                }
            }

            override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
