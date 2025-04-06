package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari.BenzerFilmlerFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari.FilmDetailsFragment
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari.OnerilenFilmlerFragment
import com.example.poppcornapplicationnew.databinding.FragmentMovieDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class MovieDetails : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val bundle: MovieDetailsArgs by navArgs()
        val Movie = bundle.myMovie




        // Fragmentlere Movie nesnesini gönder
        val bundleFilmDetailsFragment = FilmDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", Movie) // Movie nesnesini Bundle'a koy
            }
        }

        val bundleBenzerFilmlerFragment = BenzerFilmlerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", Movie) // Movie nesnesini Bundle'a koy
            }
        }
        val bundleOnerilenFilmlerFragment = OnerilenFilmlerFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", Movie) // Movie nesnesini Bundle'a koy
            }
        }




        fragmentList.add(bundleFilmDetailsFragment)
        fragmentList.add(bundleBenzerFilmlerFragment)
        fragmentList.add(bundleOnerilenFilmlerFragment)



        val adapter = MyViewPagerAdapter(requireActivity())
        binding.viewPager23.adapter = adapter

        fragmentTitleList.add("Hakkında")
        fragmentTitleList.add("Benzerler")
        fragmentTitleList.add("Önerilenler")


        TabLayoutMediator(binding.tabLayout, binding.viewPager23) { tab, position ->
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
