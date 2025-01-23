package com.example.poppcornapplicationnew

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poppcornapplicationnew.databinding.FragmentFilmDetaylarBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentFilmDetaylar : Fragment() {
    private lateinit var binding: FragmentFilmDetaylarBinding
    private lateinit var mddi: MovieDetailsDaoInterface
    private var yeniFilm: MediaDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDetaylarBinding.inflate(inflater, container, false)
        mddi = ApiUtils.getMovieDetailsDaoInterface()

        val movie = arguments?.getParcelable<Movie>("movie")
        if (movie == null) {
            Log.e("FragmentFilmDetaylar", "Movie argümanı eksik")
        } else {
            getFilmlerDetails(movie.id)
        }

        return binding.root
    }

    private fun getFilmlerDetails(id: Int) {
        mddi.getMovieDetails(movieId = id).enqueue(object : Callback<MediaDetails> {
            override fun onResponse(call: Call<MediaDetails>, response: Response<MediaDetails>) {
                if (response.body() != null) {
                    yeniFilm = response.body()

                    yeniFilm?.let { film ->
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

            override fun onFailure(call: Call<MediaDetails>, t: Throwable) {
                Log.e("Hata", t.message.toString())
            }
        })
    }
}
