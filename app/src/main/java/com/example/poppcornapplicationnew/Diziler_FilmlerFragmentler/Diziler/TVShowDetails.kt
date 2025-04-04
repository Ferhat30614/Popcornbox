package com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Diziler

import DiziDetailsFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Diziler.TabYapilari.BenzerDizilerFragment
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Diziler.TabYapilari.OnerilenDizilerFragment
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler.TabYapilari.FragmentBenzerFilmler
import com.example.poppcornapplicationnew.databinding.FragmentTvShowDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class TVShowDetails : Fragment() {
    private lateinit var binding: FragmentTvShowDetailsBinding
    private val fragmentListesi = ArrayList<Fragment>()
    private val fragmentBaslikListesi = ArrayList<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //FragmentMovieDetailsBinding.inflate(inflater, container, false)

        binding = FragmentTvShowDetailsBinding.inflate(inflater,container,false)
        val bundle: TVShowDetailsArgs by navArgs()
        val TVShow = bundle.mydizi

        // Fragmentlere Movie nesnesini gönder
        val DiziDetails = DiziDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // Movie nesnesini Bundle'a koy
            }
        }

        val fragmentBenzerFilmler = FragmentBenzerFilmler().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // Movie nesnesini Bundle'a koy
            }
        }


        fragmentListesi.add(DiziDetailsFragment())
        fragmentListesi.add(BenzerDizilerFragment())
        fragmentListesi.add(OnerilenDizilerFragment())



        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewpager1.adapter = adapter

        fragmentBaslikListesi.add("Hakkında")
        fragmentBaslikListesi.add("Benzerler")
        fragmentBaslikListesi.add("Önerilenler")


        TabLayoutMediator(binding.tablayout1, binding.viewpager1) { tab, position ->
            tab.setText(fragmentBaslikListesi[position])
        }.attach()



        return binding.root
    }


    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return fragmentListesi.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentListesi[position]
        }

    }


}