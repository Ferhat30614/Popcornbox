package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler
import DiziDetailsFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import  com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.BenzerDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.OnerilenDizilerFragment
import com.example.poppcornapplicationnew.databinding.FragmentTvShowDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class TVShowDetails : Fragment() {
    private lateinit var binding: FragmentTvShowDetailsBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentTvShowDetailsBinding.inflate(inflater,container,false)
        val bundle: TVShowDetailsArgs by navArgs()
        val TVShow = bundle.mydizi



        // Fragmentlere TVShow nesnesini gönder
        val bundleDiziDetailsFragment = DiziDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // Movie nesnesini Bundle'a koy
            }
        }

        val bundleBenzerDizilerFragment = BenzerDizilerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow) // tvshow nesnesini Bundle'a koy
            }
        }


        fragmentList.add(bundleDiziDetailsFragment)
        fragmentList.add(bundleBenzerDizilerFragment)
        fragmentList.add(OnerilenDizilerFragment())



        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewpager1.adapter = adapter

        fragmentTitleList.add("Hakkında")
        fragmentTitleList.add("Benzerler")
        fragmentTitleList.add("Önerilenler")


        TabLayoutMediator(binding.tablayout1, binding.viewpager1) { tab, position ->
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