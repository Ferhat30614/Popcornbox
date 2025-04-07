package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.Seconds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.databinding.FragmentSecondTVShowDetailsBinding
import com.example.poppcornapplicationnew.databinding.FragmentTvShowDetailsBinding
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.Seconds.SecondTabYapilari.SecondBenzerDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.Seconds.SecondTabYapilari.SecondDiziDetailsFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.Seconds.SecondTabYapilari.SecondOnerilenDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.TVShowDetailsArgs
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.BenzerDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.OnerilenDizilerFragment
import com.google.android.material.tabs.TabLayoutMediator


class SecondTVShowDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSecondTVShowDetailsBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("SecondTVShowDetails Fragment ","SecondTVShowDetails Fragment Açıldı")

        binding = FragmentSecondTVShowDetailsBinding.inflate(inflater,container,false)
        val bundle: TVShowDetailsArgs by navArgs()
        val TVShow = bundle.mydizi

        if (TVShow==null){
            Log.e("SecondTVShowDetails Fragment Sayfası","TvShow Argümanı Eksik")
        }





        // Fragmentlere TVShow nesnesini gönder
        val bundleSecondDiziDetailsFragment = SecondDiziDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // Movie nesnesini Bundle'a koy
            }
        }

        val bundleSecondBenzerDizilerFragment = SecondBenzerDizilerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // tvshow nesnesini Bundle'a koy
            }
        }


        fragmentList.add(bundleSecondDiziDetailsFragment)
        fragmentList.add(bundleSecondBenzerDizilerFragment)
        fragmentList.add(SecondOnerilenDizilerFragment())



        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewPager2.adapter = adapter

        fragmentTitleList.add("Hakkında")
        fragmentTitleList.add("Benzerler")
        fragmentTitleList.add("Önerilenler")


        TabLayoutMediator(binding.tablayout2, binding.viewPager2) { tab, position ->
            tab.setText(fragmentTitleList[position])
        }.attach()

        return binding.root
    }


    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}