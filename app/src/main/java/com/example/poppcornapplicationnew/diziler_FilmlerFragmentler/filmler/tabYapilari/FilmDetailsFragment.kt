package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.filmler.tabYapilari

import com.example.poppcornapplicationnew.entities.mediaDetailResponse.MediaDetailResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.entities.movieResponse.Movie
import com.example.poppcornapplicationnew.retrofit.MovieDetailsDaoInterface
import com.example.poppcornapplicationnew.R
import com.example.poppcornapplicationnew.databinding.FragmentFilmDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFilmDetailsBinding
    private lateinit var MovieDetailsDaoInterface: MovieDetailsDaoInterface
    private var movieDetail: MediaDetailResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.e("FilmDetails Fragment ","FilmDetails Fragment Açıldı")
        binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        MovieDetailsDaoInterface = ApiUtils.getMovieDetailsDaoInterface()

        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("FragmentFilmDetaylar", "Movie argümanı eksik")
        } else {
            getFilmlerDetails(movie.id)
        }



        return binding.root
    }

    private fun getFilmlerDetails(id: Int) {
        MovieDetailsDaoInterface.getMovieDetails(movieId = id).enqueue(object : Callback<MediaDetailResponse> {
            override fun onResponse(call: Call<MediaDetailResponse>, response: Response<MediaDetailResponse>) {
                if (response.body() != null) {
                    movieDetail = response.body()

                    movieDetail?.let { film ->
                        // Poster Yükleme
                        film.backdropPath?.let { backdropPath ->
                            Picasso.get().load("https://image.tmdb.org/t/p/w500$backdropPath")
                                .placeholder(R.drawable.yukleniyo)
                                .error(R.drawable.interstellar)
                                .into(binding.ivMoviePoster)
                        }

                        // Özeti Ayarla
                        binding.tvOverview.text = film.overview ?: "Özet bilgisi bulunmuyor."

                        // Türleri Tek Bir TextView'e Yaz
                        val genresText = film.genres?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvGenres.text = genresText ?: "Tür bilgisi bulunmuyor."

                        // Çıkış Tarihi
                        binding.tvReleaseDate.text = "Çıkış Tarihi: ${film.releaseDate ?: "Bilinmiyor"}"

                        // Orijinal Başlık
                        binding.tvOriginalTitle.text = "Orijinal İsmi: ${film.originalTitle ?: "Bilinmiyor"}"

                        // Durum
                        binding.tvStatus.text = "Durumu: ${film.status ?: "Bilinmiyor"}"

                        // Süre
                        binding.tvRuntime.text = "Süresi: ${film.runtime?.let { "$it dakika" } ?: "Bilinmiyor"}"

                        // Orijinal Dil
                        binding.tvOriginalLanguage.text = "Orijinal Dili: ${film.originalLanguage ?: "Bilinmiyor"}"

                        // Yapımcı Ülkeler
                        val productionCountriesText = film.productionCountries?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvProductionCountry.text = "Yapımcı Ülke: ${productionCountriesText ?: "Bilinmiyor"}"

                        // Yapımcı Şirketler
                        val productionCompaniesText = film.productionCompanies?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvProductionCompanies.text = "Şirketler: ${productionCompaniesText ?: "Bilinmiyor"}"

                        // Bütçe
                        binding.tvBudget.text = "Bütçesi: ${film.budget?.let { "$$it" } ?: "Bilinmiyor"}"

                        // Hasılat
                        binding.tvRevenue.text = "Hasılatı: ${film.revenue?.let { "$$it" } ?: "Bilinmiyor"}"

                        // Tagline
                        binding.tvTagline.text = "Tagline: ${film.tagline ?: "Yok"}"

                        // Ortalama Puan
                        binding.tvVoteAverage.text = "Puan Ortalaması: ${film.voteAverage?.toString() ?: "Bilinmiyor"}"

                        // Oy Sayısı
                        binding.tvVoteCount.text = "Oy Sayısı: ${film.voteCount?.toString() ?: "Bilinmiyor"}"
                    }
                } else {
                    Log.e("Apiden mesaj", "Response null döndü")
                }
            }

            override fun onFailure(call: Call<MediaDetailResponse>, t: Throwable) {
                Log.e("Hata", t.message.toString())
            }
        })
    }
}
