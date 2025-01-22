package com.example.poppcornapplicationnew

import SpokenLanguage
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaDetails(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,                      // İçerik yetişkinlere uygun mu?

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,               // Arka plan görselinin yolu

    @SerializedName("belongs_to_collection")
    @Expose
    val belongsToCollection: BelongsToCollection?, // Film bir seriye ait mi?

    @SerializedName("budget")
    @Expose
    val budget: Int?,                         // Filmin bütçesi

    @SerializedName("homepage")
    @Expose
    val homepage: String?,                    // Filmin ana sayfası

    @SerializedName("id")
    @Expose
    val id: Int?,                             // Medyanın benzersiz ID'si

    @SerializedName("imdb_id")
    @Expose
    val imdbId: String?,                      // IMDb ID'si

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String?,           // Orijinal dil

    @SerializedName("original_title")
    @Expose
    val originalTitle: String?,              // Filmin orijinal başlığı

    @SerializedName("overview")
    @Expose
    val overview: String?,                   // İçerik özeti

    @SerializedName("popularity")
    @Expose
    val popularity: Double?,                 // Popülerlik puanı

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,                 // Poster görselinin yolu

    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<ProductionCompany>?, // Yapımcı şirketler


    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>?, // Yapımcı ülkelerin kısaltmaları


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

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage>?, // Konuşulan diller

    @SerializedName("status")
    @Expose
    val status: String?,                     // İçerik durumu

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>?,

    @SerializedName("tagline")
    @Expose
    val tagline: String?,                    // Slogan

    @SerializedName("title")
    @Expose
    val title: String?,                      // Filmin başlığı

    @SerializedName("video")
    @Expose
    val video: Boolean?,                     // Videonun olup olmadığı

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double?,                // Ortalama oy puanı

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int?                      // Toplam oy sayısı
) : Parcelable
