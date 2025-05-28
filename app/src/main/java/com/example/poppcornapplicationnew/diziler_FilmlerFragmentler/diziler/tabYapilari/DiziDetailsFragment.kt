package com.example.poppcornapplicationnew

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.poppcornapplicationnew.databinding.FragmentDiziDetailsBinding
import com.example.poppcornapplicationnew.entities.mediaDetailResponse.MediaDetailResponse
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.retrofit.TvDetailsDaoInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiziDetailsFragment : Fragment() {
    private lateinit var binding: FragmentDiziDetailsBinding
    private lateinit var tvDetailsDaoInterface: TvDetailsDaoInterface
    private lateinit var likeDao: LikeDao
    private var tvshowDetail: MediaDetailResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiziDetailsBinding.inflate(inflater, container, false)
        tvDetailsDaoInterface = ApiUtils.getTvDetailsDaoInterface()
        likeDao = LikeDao(requireContext())

        val tvshow = arguments?.getParcelable<TVShow>("dizi")

        if (tvshow != null) {
            getDiziDetails(tvshow.id)

            binding.btnLike.setOnClickListener {
                tvshowDetail?.let { detail ->
                    val current = likeDao.getLikeStatus(detail.id!!)
                    if (current?.liked == 1) {
                        likeDao.deleteLike(detail.id!!)
                        updateLikeButtons(null)
                    } else {
                        val like = Likes(
                            contentId = detail.id!!,
                            title = detail.name ?: "",
                            type = "tv",
                            liked = 1,
                            rating = detail.voteAverage ?: 0.0,
                            genres = detail.genres?.joinToString(",") { it.name ?: "" } ?: ""
                        )
                        likeDao.insertOrUpdateLike(like)
                        updateLikeButtons(1)
                    }
                }
            }

            binding.btnDislike.setOnClickListener {
                tvshowDetail?.let { detail ->
                    val current = likeDao.getLikeStatus(detail.id!!)
                    if (current?.liked == 0) {
                        likeDao.deleteLike(detail.id!!)
                        updateLikeButtons(null)
                    } else {
                        val dislike = Likes(
                            contentId = detail.id!!,
                            title = detail.name ?: "",
                            type = "tv",
                            liked = 0,
                            rating = detail.voteAverage ?: 0.0,
                            genres = detail.genres?.joinToString(",") { it.name ?: "" } ?: ""
                        )
                        likeDao.insertOrUpdateLike(dislike)
                        updateLikeButtons(0)
                    }
                }
            }
        }

        return binding.root
    }

    private fun updateLikeButtons(status: Int?) {
        when (status) {
            1 -> {
                binding.btnLike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.Kırmızı))
                binding.btnDislike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
            }
            0 -> {
                binding.btnLike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                binding.btnDislike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.Kırmızı))
            }
            else -> {
                binding.btnLike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                binding.btnDislike.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
    }

    private fun getDiziDetails(id: Int) {
        tvDetailsDaoInterface.getTvShowDetails(tvId = id).enqueue(object : Callback<MediaDetailResponse> {
            override fun onResponse(call: Call<MediaDetailResponse>, response: Response<MediaDetailResponse>) {
                if (response.body() != null) {
                    tvshowDetail = response.body()
                    tvshowDetail?.let { dizi ->
                        val status = likeDao.getLikeStatus(dizi.id!!)
                        updateLikeButtons(status?.liked)

                        dizi.backdropPath?.let { backdropPath ->
                            Picasso.get().load("https://image.tmdb.org/t/p/w500$backdropPath")
                                .placeholder(R.drawable.yukleniyo)
                                .error(R.drawable.interstellar)
                                .into(binding.ivDiziPoster)
                        }

                        binding.tvOverview.text = dizi.overview ?: "Özet bilgisi bulunmuyor."
                        val genresText = dizi.genres?.joinToString(", ") { it.name ?: "" }
                        binding.tvGenres.text = genresText ?: "Tür bilgisi bulunmuyor."
                        binding.tvFirstAirDate.text = "İlk Yayın Tarihi: ${dizi.firstAirDate ?: "Bilinmiyor"}"
                        binding.tvOriginalName.text = "Orijinal İsmi: ${dizi.originalName ?: "Bilinmiyor"}"
                        binding.tvStatus.text = "Durumu: ${dizi.status ?: "Bilinmiyor"}"
                        val episodeRuntime = dizi.episodeRunTime?.joinToString(", ") { "$it dakika" }
                        binding.tvEpisodeRuntime.text = "Bölüm Süresi: ${episodeRuntime ?: "Bilinmiyor"}"
                        binding.tvOriginalLanguage.text = "Orijinal Dili: ${dizi.originalLanguage ?: "Bilinmiyor"}"
                        val productionCountries = dizi.productionCountries?.joinToString(", ") { it.name ?: "" }
                        binding.tvProductionCountry.text = "Yapımcı Ülke: ${productionCountries ?: "Bilinmiyor"}"
                        val networks = dizi.networks?.joinToString(", ") { it.name ?: "" }
                        binding.tvNetworks.text = "Yayıncı Ağ: ${networks ?: "Bilinmiyor"}"
                        binding.tvSeasons.text = "Sezon Sayısı: ${dizi.numberOfSeasons ?: "Bilinmiyor"}"
                        binding.tvEpisodes.text = "Bölüm Sayısı: ${dizi.numberOfEpisodes ?: "Bilinmiyor"}"
                        binding.tvVoteAverage.text = "Puan Ortalaması: ${dizi.voteAverage ?: "Bilinmiyor"}"
                        binding.tvVoteCount.text = "Oy Sayısı: ${dizi.voteCount ?: "Bilinmiyor"}"
                        binding.tvTagline.text = "Tagline: ${dizi.tagline ?: "Yok"}"
                    }
                } else {
                    Log.e("DiziDetails", "Response null döndü")
                }
            }

            override fun onFailure(call: Call<MediaDetailResponse>, t: Throwable) {
                Log.e("Hata", t.message.toString())
            }
        })
    }
}
