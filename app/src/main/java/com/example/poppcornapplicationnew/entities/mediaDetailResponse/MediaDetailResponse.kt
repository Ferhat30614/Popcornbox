package com.example.poppcornapplicationnew.entities.mediaDetailResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaDetailResponse(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,                      // İçerik yetişkinlere uygun mu?

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,               // Arka plan görselinin yolu

    @SerializedName("belongs_to_collection")
    @Expose
    val belongsToCollection: BelongsToCollection?, // Film bir seriye ait mi?

    @SerializedName("original_title")
    @Expose
    val originalTitle: String?,              // Filmin orijinal başlığı

    @SerializedName("budget")
    @Expose
    val budget: Int?,                         // Filmin bütçesi

    @SerializedName("created_by")
    @Expose
    val createdBy: List<CreatedBy>?,         // Diziyi oluşturan kişiler (Eksik Alan)

    @SerializedName("episode_run_time")
    @Expose
    val episodeRunTime: List<Int>?,          // Bölümlerin süreleri (Eksik Alan)

    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String?,               // İlk yayın tarihi (Eksik Alan)

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>?,                // Türler

    @SerializedName("homepage")
    @Expose
    val homepage: String?,                   // Ana sayfa

    @SerializedName("id")
    @Expose
    val id: Int?,                             // Medyanın benzersiz ID'si

    @SerializedName("imdb_id")
    @Expose
    val imdbId: String?,                      // IMDb ID'si

    @SerializedName("in_production")
    @Expose
    val inProduction: Boolean?,              // Yapım aşamasında mı? (Eksik Alan)

    @SerializedName("languages")
    @Expose
    val languages: List<String>?,            // Kullanılan diller (Eksik Alan)

    @SerializedName("last_air_date")
    @Expose
    val lastAirDate: String?,                // Son yayın tarihi (Eksik Alan)

    @SerializedName("last_episode_to_air")
    @Expose
    val lastEpisodeToAir: LastEpisodeToAir?, // Son bölüm bilgisi (Eksik Alan)

    @SerializedName("name")
    @Expose
    val name: String?,                       // Dizinin adı (Eksik Alan)

    @SerializedName("networks")
    @Expose
    val networks: List<Network>?,            // Yayıncı ağlar (Eksik Alan)

    @SerializedName("number_of_episodes")
    @Expose
    val numberOfEpisodes: Int?,              // Bölüm sayısı (Eksik Alan)

    @SerializedName("number_of_seasons")
    @Expose
    val numberOfSeasons: Int?,               // Sezon sayısı (Eksik Alan)

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String?,           // Orijinal dil

    @SerializedName("original_name")
    @Expose
    val originalName: String?,               // Orijinal başlık (Eksik Alan)

    @SerializedName("overview")
    @Expose
    val overview: String?,                   // İçerik özeti

    @SerializedName("popularity")
    @Expose
    val popularity: Double?,                 // Popülerlik puanı

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,                 // Poster görseli

    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<ProductionCompany>?, // Yapımcı şirketler

    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>?,        // Yapımcı ülkeler

    @SerializedName("production_countries")
    @Expose
    val productionCountries: List<ProductionCountry>?, // Yapımcı ülkeler

    @SerializedName("release_date")
    @Expose
    val releaseDate: String?,                // Filmin vizyon tarihi

    @SerializedName("revenue")
    @Expose
    val revenue: Long?,                      // Filmin hasılatı

    @SerializedName("runtime")
    @Expose
    val runtime: Int?,                       // Filmin süresi

    @SerializedName("seasons")
    @Expose
    val seasons: List<Season>?,              // Sezonlar (Eksik Alan)

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage>?, // Konuşulan diller

    @SerializedName("status")
    @Expose
    val status: String?,                     // İçerik durumu

    @SerializedName("tagline")
    @Expose
    val tagline: String?,                    // Slogan

    @SerializedName("title")
    @Expose
    val title: String?,                      // Filmin başlığı

    @SerializedName("video")
    @Expose
    val video: Boolean?,                     // Videonun olup olmadığı

    @SerializedName("type")
    @Expose
    val type: String?,

    @SerializedName("next_episode_to_air")
    @Expose
    val nextEpisodeToAir: LastEpisodeToAir?, // Aynı yapıdaki bir model



    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double?,                // Ortalama oy puanı

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int?                      // Toplam oy sayısı
) : Parcelable
