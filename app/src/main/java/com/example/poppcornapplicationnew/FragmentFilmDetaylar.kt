package com.example.poppcornapplicationnew

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poppcornapplicationnew.databinding.FragmentFilmDetaylarBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentFilmDetaylar : Fragment() {
    private lateinit var binding: FragmentFilmDetaylarBinding
    private lateinit var mddi: MovieDetailsDaoInterface
    private var yeniFilm: MediaDetails? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentFilmDetaylarBinding.inflate(inflater,container,false)

        mddi=ApiUtils.getMovieDetailsDaoInterface()




        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("FragmentFilmDetaylar", "Movie argümanı eksik")
        } else {
            getFilmlerDetails(movie.id)
        }









        return binding.root
    }

    private fun getFilmlerDetails(id:Int){

        mddi.getMovieDetails(movieId = id).enqueue(object: Callback<MediaDetails> {
            override fun onResponse(call: Call<MediaDetails>?, response: Response<MediaDetails>) {

                if (response.body() != null) {
                    yeniFilm=response.body()


                    yeniFilm?.let {
                        // Arka plan posteri yükle
                        it.backdropPath?.let { backdropPath ->
                            Picasso.get().load("https://image.tmdb.org/t/p/w500$backdropPath")
                                .placeholder(R.drawable.yukleniyo)
                                .error(R.drawable.interstellar)
                                .into(binding.ivMoviePoster)
                        }

                        // Özeti ayarla
                        binding.tvOverview.text = it.overview ?: "Özet bilgisi bulunmuyor."

                        // Çıkış tarihini ayarla
                        binding.tvReleaseDate.text = "Çıkış Tarihi: ${it.releaseDate ?: "Bilinmiyor"}"

                        // Orijinal ismi ayarla
                        binding.tvOriginalTitle.text = "Orijinal İsmi: ${it.originalTitle ?: "Bilinmiyor"}"

                        // Durumu ayarla
                        binding.tvStatus.text = "Durumu: ${it.status ?: "Bilinmiyor"}"

                        // Süreyi ayarla
                        binding.tvRuntime.text = "Süresi: ${it.runtime?.let { "$it dakika" } ?: "Bilinmiyor"}"

                        // Orijinal dili ayarla
                        binding.tvOriginalLanguage.text = "Orijinal Dili: ${it.originalLanguage ?: "Bilinmiyor"}"

                        // Bütçeyi ayarla
                        binding.tvBudget.text = "Bütçesi: ${it.budget?.let { "$$it" } ?: "Bilinmiyor"}"

                        // Hasılatı ayarla
                        binding.tvRevenue.text = "Hasılatı: ${it.revenue?.let { "$$it" } ?: "Bilinmiyor"}"

                        // Sloganı ayarla
                        binding.tvTagline.text = "Tagline: ${it.tagline ?: "Yok"}"

                        // Ortalama puanı ayarla
                        binding.tvVoteAverage.text = "Puan Ortalaması: ${it.voteAverage?.toString() ?: "Bilinmiyor"}"

                        // Oy sayısını ayarla
                        binding.tvVoteCount.text = "Oy Sayısı: ${it.voteCount?.toString() ?: "Bilinmiyor"}"
                    }





                }else{
                    Log.e("Apiden mesaj","Response null döndü")

                }


            }

            override fun onFailure(call: Call<MediaDetails>?, t: Throwable?) {


            }
        })

    }







}
