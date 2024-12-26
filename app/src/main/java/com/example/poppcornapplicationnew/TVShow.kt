package com.example.poppcornapplicationnew

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShow(
    @SerializedName("adult")
    val adult: Boolean,                   // Dizi yetişkin içerik mi
    @SerializedName("backdrop_path")
    val backdropPath: String?,            // Arka plan resminin yolu (nullable olabilir)
    @SerializedName("genre_ids")
    val genreIds: ArrayList<Int>,         // Türlerin ID'leri için ArrayList
    @SerializedName("id")
    val id: Int,                          // Dizinin benzersiz kimliği
    @SerializedName("origin_country")
    val originCountry: ArrayList<String>, // Dizinin menşe ülkesi (birden fazla ülke olabilir)
    @SerializedName("original_language")
    val originalLanguage: String,         // Dizinin orijinal dili
    @SerializedName("original_name")
    val originalName: String,             // Dizinin orijinal adı
    @SerializedName("overview")
    val overview: String,                 // Dizinin açıklaması
    @SerializedName("popularity")
    val popularity: Double,               // Dizinin popülerlik puanı
    @SerializedName("poster_path")
    val posterPath: String?,              // Poster resminin yolu (nullable olabilir)
    @SerializedName("first_air_date")
    val firstAirDate: String,             // Dizinin ilk yayın tarihi
    @SerializedName("name")
    val name: String,                     // Dizinin adı
    @SerializedName("vote_average")
    val voteAverage: Double,              // Oy ortalaması
    @SerializedName("vote_count")
    val voteCount: Int                    // Oy sayısı
) : Parcelable
