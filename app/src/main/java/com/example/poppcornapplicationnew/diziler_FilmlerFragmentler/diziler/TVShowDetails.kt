package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler

import DiziDetailsFragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.poppcornapplicationnew.R
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.BenzerDizilerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari.OnerilenDizilerFragment
import com.example.poppcornapplicationnew.databinding.FragmentTvShowDetailsBinding
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.google.android.material.tabs.TabLayoutMediator

class TVShowDetails : Fragment(), OnerilenDizilerFragment.OnOnerilenClickListener {
    private lateinit var binding: FragmentTvShowDetailsBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = arrayListOf("Hakkında", "Benzerler", "Önerilenler")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("TVShowDetails Fragment", "TVShowDetails Fragment Açıldı")

        binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)

        // Argümanı al
        val args: TVShowDetailsArgs by navArgs()
        val tvShow = args.mydizi

        // Hakkında tabı
        val diziDetailsFragment = DiziDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", tvShow)
            }
        }

        // Benzerler tabı
        val benzerDizilerFragment = BenzerDizilerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("dizi", tvShow)
            }
        }

        // Önerilenler tabı
        val onerilenDizilerFragment = OnerilenDizilerFragment().apply {
            setOnClickListener(this@TVShowDetails) //  Buraya dikkat
        }

        fragmentList.clear()
        fragmentList.add(diziDetailsFragment)
        fragmentList.add(benzerDizilerFragment)
        fragmentList.add(onerilenDizilerFragment)

        // ViewPager setup
        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewpager1.adapter = adapter

        TabLayoutMediator(binding.tablayout1, binding.viewpager1) { tab, position ->
            tab.text = fragmentTitleList[position]
        }.attach()

        return binding.root
    }

    // 🔁 Sonsuz döngü burada başlıyor dcd
    override fun onOnerilenClick(tvShow: TVShow) {
        val action = TVShowDetailsDirections.actionTVShowDetailsSelf(tvShow)
        findNavController().navigate(action)
    }

    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack(R.id.dizilerFragment, false)
                }
            }
        )
    }





}
