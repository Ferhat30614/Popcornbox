package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler
import DiziDetailsFragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import  com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.BenzerDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.OnerilenDizilerFragment
import com.example.poppcornapplicationnew.databinding.FragmentTvShowDetailsBinding
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.google.android.material.tabs.TabLayoutMediator


class TVShowDetails : Fragment(), OnerilenDizilerFragment.OnOnerilenClickListener {
    private lateinit var binding: FragmentTvShowDetailsBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TVShowDetails Fragment", "TVShowDetails Fragment Açıldı")

        binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        val bundle: TVShowDetailsArgs by navArgs()
        val TVShow = bundle.mydizi

        val bundleDiziDetailsFragment = DiziDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow)
            }
        }

        val bundleBenzerDizilerFragment = BenzerDizilerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", TVShow)
            }
        }

        val onerilenDizilerFragment = OnerilenDizilerFragment()
        onerilenDizilerFragment.setOnClickListener(this) // 👈 önemli satır

        fragmentList.add(bundleDiziDetailsFragment)
        fragmentList.add(bundleBenzerDizilerFragment)
        fragmentList.add(onerilenDizilerFragment)

        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewpager1.adapter = adapter

        fragmentTitleList.add("Hakkında")
        fragmentTitleList.add("Benzerler")
        fragmentTitleList.add("Önerilenler")

        TabLayoutMediator(binding.tablayout1, binding.viewpager1) { tab, position ->
            tab.text = fragmentTitleList[position]
        }.attach()

        return binding.root
    }

    override fun onOnerilenClick(tvShow: TVShow) {
        val action = TVShowDetailsDirections.actionTVShowDetailsToSecondTVShowDetailsFragment(tvShow)
        findNavController().navigate(action)
    }

    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}
