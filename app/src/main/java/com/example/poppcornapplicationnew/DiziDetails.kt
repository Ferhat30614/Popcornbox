package com.example.poppcornapplicationnew

import MediaDetails
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poppcornapplicationnew.databinding.FragmentDiziDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiziDetails : Fragment() {
    private lateinit var binding: FragmentDiziDetailsBinding
    private lateinit var tvDetailsDaoInterface: TvDetailsDaoInterface
    private var diziDetay: MediaDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiziDetailsBinding.inflate(inflater, container, false)
        tvDetailsDaoInterface = ApiUtils.getTvDetailsDaoInterface()

        // Gelen dizi argümanını alıyoruz
        val dizi = arguments?.getParcelable<TVShow>("dizi")

        if (dizi == null) {
            Log.e("DiziDetails", "Dizi argümanı eksik")
        } else {
            getDiziDetails(dizi.id)
        }

        return binding.root
    }

    private fun getDiziDetails(id: Int) {
        tvDetailsDaoInterface.getTvShowDetails(tvId = id).enqueue(object : Callback<MediaDetails> {
            override fun onResponse(call: Call<MediaDetails>, response: Response<MediaDetails>) {
                if (response.body() != null) {
                    diziDetay = response.body()

                    diziDetay?.let { dizi ->
                        // Poster Yükleme
                        dizi.backdropPath?.let { backdropPath ->
                            Picasso.get().load("https://image.tmdb.org/t/p/w500$backdropPath")
                                .placeholder(R.drawable.yukleniyo)
                                .error(R.drawable.interstellar)
                                .into(binding.ivDiziPoster)
                        }

                        // Özeti Ayarla
                        binding.tvOverview.text = dizi.overview ?: "Özet bilgisi bulunmuyor."

                        // Türleri Tek Bir TextView'e Yaz
                        val genresText = dizi.genres?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvGenres.text = genresText ?: "Tür bilgisi bulunmuyor."

                        // İlk Yayın Tarihi
                        binding.tvFirstAirDate.text = "İlk Yayın Tarihi: ${dizi.firstAirDate ?: "Bilinmiyor"}"

                        // Orijinal Başlık
                        binding.tvOriginalName.text = "Orijinal İsmi: ${dizi.originalName ?: "Bilinmiyor"}"

                        // Durum
                        binding.tvStatus.text = "Durumu: ${dizi.status ?: "Bilinmiyor"}"

                        // Bölüm Süreleri
                        val episodeRuntime = dizi.episodeRunTime?.joinToString(separator = ", ") { "$it dakika" }
                        binding.tvEpisodeRuntime.text = "Bölüm Süresi: ${episodeRuntime ?: "Bilinmiyor"}"

                        // Orijinal Dil
                        binding.tvOriginalLanguage.text = "Orijinal Dili: ${dizi.originalLanguage ?: "Bilinmiyor"}"

                        // Yapımcı Ülkeler
                        val productionCountriesText = dizi.productionCountries?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvProductionCountry.text = "Yapımcı Ülke: ${productionCountriesText ?: "Bilinmiyor"}"

                        // Yayıncı Ağlar
                        val networksText = dizi.networks?.joinToString(separator = ", ") { it.name ?: "" }
                        binding.tvNetworks.text = "Yayıncı Ağ: ${networksText ?: "Bilinmiyor"}"

                        // Sezon ve Bölüm Bilgisi
                        binding.tvSeasons.text = "Sezon Sayısı: ${dizi.numberOfSeasons ?: "Bilinmiyor"}"
                        binding.tvEpisodes.text = "Bölüm Sayısı: ${dizi.numberOfEpisodes ?: "Bilinmiyor"}"

                        // Ortalama Puan
                        binding.tvVoteAverage.text = "Puan Ortalaması: ${dizi.voteAverage?.toString() ?: "Bilinmiyor"}"

                        // Oy Sayısı
                        binding.tvVoteCount.text = "Oy Sayısı: ${dizi.voteCount?.toString() ?: "Bilinmiyor"}"

                        // Tagline
                        binding.tvTagline.text = "Tagline: ${dizi.tagline ?: "Yok"}"
                    }
                } else {
                    Log.e("DiziDetails", "Response null döndü")
                }
            }

            override fun onFailure(call: Call<MediaDetails>, t: Throwable) {
                Log.e("Hata", t.message.toString())
            }
        })
    }
}
