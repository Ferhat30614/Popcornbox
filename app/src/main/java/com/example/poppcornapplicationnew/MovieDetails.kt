package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.poppcornapplicationnew.databinding.FragmentMovieDetailsBinding


class MovieDetails : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val fragmentListesi = ArrayList<Fragment>()
    private val fragmentBaslikListesi = ArrayList<String>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMovieDetailsBinding.inflate(inflater,container,false)
        val bundle:MovieDetailsArgs by navArgs()
        val Movie=bundle.myMovie

        fragmentListesi.add(FragmentFilmDetaylar())
        fragmentListesi.add(FragmentBenzerler())


        val adapter = MyViewPagerAdapter(this)
        viewpager2.adapter = adapter

        fragmentBaslikListesi.add("Bir")
        fragmentBaslikListesi.add("İki")
        fragmentBaslikListesi.add("Üç")

        TabLayoutMediator(tablayout,viewpager2){tab, position ->
            tab.setText(fragmentBaslikListesi[position])
        }.attach()










        return binding.root
    }


    inner class MyViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            return fragmentListesi.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentListesi[position]
        }

    }









}