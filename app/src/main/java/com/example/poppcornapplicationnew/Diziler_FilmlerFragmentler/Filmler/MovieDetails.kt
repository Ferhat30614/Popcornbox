package com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler.TabYapilari.FragmentBenzerFilmler
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler.TabYapilari.FragmentFilmDetaylar
import com.example.poppcornapplicationnew.Diziler_FilmlerFragmentler.Filmler.TabYapilari.OnerilenFilmlerFragment
import com.example.poppcornapplicationnew.databinding.FragmentMovieDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class MovieDetails : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val fragmentListesi = ArrayList<Fragment>()
    private val fragmentBaslikListesi = ArrayList<String>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val bundle: MovieDetailsArgs by navArgs()
        val Movie = bundle.myMovie





        // Fragmentlere Movie nesnesini gönder
        val fragmentFilmDetaylar = FragmentFilmDetaylar().apply {
            arguments = Bundle().apply {
                putParcelable("movie", Movie) // Movie nesnesini Bundle'a koy
            }
        }

        val fragmentBenzerFilmler = FragmentBenzerFilmler().apply {
            arguments = Bundle().apply {
                putParcelable("movie", Movie) // Movie nesnesini Bundle'a koy
            }
        }


        fragmentListesi.add(fragmentFilmDetaylar)
        fragmentListesi.add(fragmentBenzerFilmler)
        fragmentListesi.add(OnerilenFilmlerFragment())



        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewPager23.adapter = adapter

        fragmentBaslikListesi.add("Hakkında")
        fragmentBaslikListesi.add("Benzerler")
        fragmentBaslikListesi.add("Önerilenler")


        TabLayoutMediator(binding.tabLayout, binding.viewPager23) { tab, position ->
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
