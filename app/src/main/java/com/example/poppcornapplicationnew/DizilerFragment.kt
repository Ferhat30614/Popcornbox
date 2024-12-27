package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poppcornapplicationnew.databinding.FragmentDizilerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DizilerFragment : Fragment() {

    private lateinit var binding: FragmentDizilerBinding
    private lateinit var adapter: TVShowAdapter
    private lateinit var gtsi: TVShowDaoInterface


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDizilerBinding.inflate(inflater, container, false)

        binding.rvTVShow.setHasFixedSize(true)
        binding.rvTVShow.layoutManager = GridLayoutManager(requireContext(), 3)


        gtsi = ApiUtils.getTVDaoInterface()
        getDiziler(1)



        return binding.root
    }

    private fun getDiziler(page: Int) {
        gtsi.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                if ( response.body() != null) {
                    val list=response.body().results

                    adapter=TVShowAdapter(requireContext(),list)
                    binding.rvTVShow.adapter=adapter



                }
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
