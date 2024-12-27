package com.example.poppcornapplicationnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poppcornapplicationnew.databinding.FragmentDizilerBinding
import com.example.poppcornapplicationnew.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMovieDetailsBinding.inflate(inflater,container,false)





        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }


}