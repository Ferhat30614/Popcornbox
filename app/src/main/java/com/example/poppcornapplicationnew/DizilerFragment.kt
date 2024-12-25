package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poppcornapplicationnew.databinding.FragmentDizilerBinding


class DizilerFragment : Fragment() {

    private lateinit var binding: FragmentDizilerBinding
    private lateinit var adapter:TVShowAdapter
    private lateinit var list:ArrayList<TVShow>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        binding=FragmentDizilerBinding.inflate(inflater,container,false)

        binding.rvTVShow.setHasFixedSize(true)
        binding.rvTVShow.layoutManager=GridLayoutManager(requireContext(),3)


        list= ArrayList()


        val tvShow1 = TVShow(false, "/path/to/backdrop1.jpg", arrayListOf(10765, 18), 1001, arrayListOf("US"), "en", "Stranger Things", "A group of kids in the 80s uncover supernatural mysteries.", 3500.25, "interstellar", "2016-07-15", "interstellar", 8.7, 9000)
        val tvShow2 = TVShow(false, "/path/to/backdrop2.jpg", arrayListOf(16, 35), 1002, arrayListOf("JP"), "ja", "Attack on Titan", "A dystopian world where humans fight against giant creatures.", 4000.5, "inception", "2013-04-07", "inception", 9.1, 15000)
        val tvShow3 = TVShow(false, "/path/to/backdrop3.jpg", arrayListOf(18, 9648), 1003, arrayListOf("UK"), "en", "Sherlock", "A modern adaptation of Sherlock Holmes solving mysteries in London.", 2800.75, "django", "2010-07-25", "django", 9.0, 8000)
        val tvShow4 = TVShow(false, "/path/to/backdrop4.jpg", arrayListOf(35, 18), 1004, arrayListOf("US"), "en", "Friends", "A comedic exploration of six friends navigating life and relationships in New York.", 4500.3, "birzamanlaranadoluda", "birzamanlaranadoluda", "Friends", 8.5, 12000)
        val tvShow5 = TVShow(false, "/path/to/backdrop5.jpg", arrayListOf(10759, 28), 1005, arrayListOf("US"), "en", "The Mandalorian", "A lone bounty hunter makes his way through the galaxy after the fall of the Empire.", 5000.2, "thehatefuleight", "thehatefuleight", "The Mandalorian", 8.8, 7000)
        val tvShow6 = TVShow(false, "/path/to/backdrop6.jpg", arrayListOf(16, 10762), 1006, arrayListOf("US"), "en", "Avatar: The Last Airbender", "A young boy must embrace his destiny as the Avatar to bring peace to the world.", 3700.6, "thepianist", "thepianist", "Avatar: The Last Airbender", 9.2, 11000)


        list.add(tvShow1)
        list.add(tvShow2)
        list.add(tvShow3)
        list.add(tvShow4)
        list.add(tvShow5)
        list.add(tvShow6)




        adapter=TVShowAdapter(requireContext(),list)

        binding.rvTVShow.adapter=adapter


        return binding.root
    }


}