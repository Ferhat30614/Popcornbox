package com.example.poppcornapplicationnew.entities.movieResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie (
    @SerializedName("adult")
    @Expose
    val adult: Boolean,                  // Filmin yetişkinlere yönelik olup olmadığı
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,           // Arka plan resminin yolu  nullable
    @SerializedName("genre_ids")
    @Expose
    val genreIds: ArrayList<Int>,        // Filmin türlerinin ID'leri
    @SerializedName("id")
    @Expose
    val id: Int,                          // Filmin benzersiz kimliği
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,        // Filmin orijinal dili
    @SerializedName("original_title")
    @Expose
    val originalTitle: String,           // Filmin orijinal başlığı
    @SerializedName("overview")
    @Expose
    val overview: String,                // Filmin özeti
    @SerializedName("popularity")
    @Expose
    val popularity: Double,              // Filmin popülerlik puanı, daha hassas değer için Double kullanılır
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,             // Filmin posterinin yolu  nullable
    @SerializedName("release_date")
    @Expose
    val releaseDate: String,             // Filmin vizyon tarihi
    @SerializedName("title")
    @Expose
    val title: String,                   // Filmin başlığı
    @SerializedName("video")
    @Expose
    val video: Boolean,                  // Videonun olup olmadığı
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,             // Ortalamada verilen oy puanı, hassasiyet için Double
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int
):Parcelable{
}