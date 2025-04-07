package com.example.poppcornapplicationnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari.OnerilenFilmlerFragmentDirections
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.squareup.picasso.Picasso

class OnerilenFilmlerAdapter (private val mcontext:Context,private var  movieArraylist:List<Movie>):RecyclerView.Adapter<OnerilenFilmlerAdapter.NesneTutucu>() {



    inner class NesneTutucu(layout:View):RecyclerView.ViewHolder(layout){

        var Card:CardView
        var imageViewFilm:ImageView

        init {
            Card=layout.findViewById(R.id.Card)
            imageViewFilm=layout.findViewById(R.id.imageViewFilm)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesneTutucu {
        val layout=LayoutInflater.from(mcontext).inflate(R.layout.card_layout,parent,false)
        return NesneTutucu(layout)

    }

    override fun getItemCount(): Int {
        return movieArraylist.size
    }

    override fun onBindViewHolder(holder: NesneTutucu, position: Int) {

        val nesnem=movieArraylist[position]


        if (nesnem.posterPath!=null)
        {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${nesnem.posterPath}")
                .placeholder(R.drawable.yukleniyo)
                .error(R.drawable.interstellar)
                .into(holder.imageViewFilm)
        }

        holder.Card.setOnClickListener(){
//OnerilenFilmlerAdapter  --> Film detaik
            // OnerilenFilmlerFragment'ten MovieDetails'e geçiş
            val action = OnerilenFilmlerFragmentDirections.actionOnerilenFilmlerFragmentToMovieDetails(nesnem)

            // NavController ile geçiş yapılırken popUpTo kullanımı
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.onerilenFilmlerFragment, true)  // Kaynak fragment
                .build()

            // Geçişi başlat
            Navigation.findNavController(holder.imageViewFilm).navigate(action, navOptions)

        }

    }




}