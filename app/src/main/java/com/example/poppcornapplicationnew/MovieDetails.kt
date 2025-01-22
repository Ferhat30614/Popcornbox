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









        return binding.root
    }









}