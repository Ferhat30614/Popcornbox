package com.example.poppcornapplicationnew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.TVShowDetailsDirections
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.squareup.picasso.Picasso

class OnerilenDizilerAdapter(
    private val fragment: Fragment,
    private val tvShowArrayList: List<TVShow>
) : RecyclerView.Adapter<OnerilenDizilerAdapter.NesneTutucu>() {

    inner class NesneTutucu(layout: View) : RecyclerView.ViewHolder(layout) {
        val card: CardView = layout.findViewById(R.id.Card)
        val imageViewFilm: ImageView = layout.findViewById(R.id.imageViewFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesneTutucu {
        val layout = LayoutInflater.from(fragment.requireContext()).inflate(R.layout.card_layout, parent, false)
        return NesneTutucu(layout)
    }

    override fun getItemCount(): Int = tvShowArrayList.size

    override fun onBindViewHolder(holder: NesneTutucu, position: Int) {
        val nesnem = tvShowArrayList[position]

        if (nesnem.posterPath != null) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${nesnem.posterPath}")
                .placeholder(R.drawable.yukleniyo)
                .error(R.drawable.interstellar)
                .into(holder.imageViewFilm)
        }

        holder.card.setOnClickListener {
            val action = TVShowDetailsDirections.actionTVShowDetailsSelf(nesnem)
            Navigation.findNavController(fragment.requireActivity(), R.id.navigation_host_fragment)
                .navigate(action)
        }
    }
}
