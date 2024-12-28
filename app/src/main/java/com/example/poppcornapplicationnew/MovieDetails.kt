package com.example.poppcornapplicationnew

import android.animation.ObjectAnimator
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
        getir()






        return binding.root
    }



    fun getir(){


/*
        val s=ObjectAnimator.ofFloat(binding.Card,"translationY",0.0f,2800.0f).apply {
            duration=2000
        }
        s.start()


        val y=ObjectAnimator.ofFloat(binding.Card,"scaleY",0.0f,1.0f).apply {
            duration=2000
        }
        y.start()


*/









    }


}