package com.example.poppcornapplicationnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TVShowAdapter(
    private val mcontext: Context,
    private val tvShowArrayList: List<TVShow>
) : RecyclerView.Adapter<TVShowAdapter.NesneTutucu>() {

    inner class NesneTutucu(layout: View) : RecyclerView.ViewHolder(layout) {
        var card: CardView = layout.findViewById(R.id.Card)
        var imageViewFilm: ImageView = layout.findViewById(R.id.imageViewFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesneTutucu {
        val layout = LayoutInflater.from(mcontext).inflate(R.layout.card_layout, parent, false)
        return NesneTutucu(layout)
    }

    override fun getItemCount(): Int {
        return tvShowArrayList.size
    }

    override fun onBindViewHolder(holder: NesneTutucu, position: Int) {
        val nesnem = tvShowArrayList[position]

        if (nesnem.posterPath != null) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${nesnem.posterPath}")
                .placeholder(R.drawable.yukleniyo)
                .error(R.drawable.interstellar)
                .into(holder.imageViewFilm)
        }

        holder.card.setOnClickListener {

        }
    }
}
