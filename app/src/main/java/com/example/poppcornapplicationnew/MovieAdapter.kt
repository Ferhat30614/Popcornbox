package com.example.poppcornapplicationnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter (private val mcontext:Context,private val  movieArraylist:List<Movie>):RecyclerView.Adapter<MovieAdapter.NesneTutucu>() {



    inner class NesneTutucu(layout:View):RecyclerView.ViewHolder(layout){

        var Card:CardView
        var imageViewFilm:ImageView
        var textViewFilmAd:TextView

        init {
            Card=layout.findViewById(R.id.Card)
            imageViewFilm=layout.findViewById(R.id.imageViewFilm)
            textViewFilmAd=layout.findViewById(R.id.textViewFilmAd)
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
        holder.textViewFilmAd.text=nesnem.title

        holder.imageViewFilm.setImageResource(mcontext.resources.getIdentifier(nesnem.posterPath,"drawable",mcontext.packageName))

        holder.Card.setOnClickListener(){


        }




    }


}