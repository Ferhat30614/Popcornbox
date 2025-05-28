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
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.MovieDetailsDirections
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.squareup.picasso.Picasso

class BenzerFilmlerAdapter(
    private val fragment: Fragment,
    private val movieArrayList: List<Movie>
) : RecyclerView.Adapter<BenzerFilmlerAdapter.NesneTutucu>() {

    inner class NesneTutucu(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.Card)
        val imageView: ImageView = view.findViewById(R.id.imageViewFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesneTutucu {
        val view = LayoutInflater.from(fragment.requireContext()).inflate(R.layout.card_layout, parent, false)
        return NesneTutucu(view)
    }

    override fun getItemCount(): Int = movieArrayList.size

    override fun onBindViewHolder(holder: NesneTutucu, position: Int) {
        val movie = movieArrayList[position]

        movie.posterPath?.let {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500$it")
                .placeholder(R.drawable.yukleniyo)
                .error(R.drawable.interstellar)
                .into(holder.imageView)
        }

        holder.card.setOnClickListener {
            val action = MovieDetailsDirections.actionMovieDetailsSelf(movie)
            Navigation.findNavController(fragment.requireActivity(), R.id.navigation_host_fragment)
                .navigate(action)
        }
    }
}
