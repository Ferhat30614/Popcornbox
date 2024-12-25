package com.example.poppcornapplicationnew

data class TVShow(
    val adult: Boolean,                   // Dizi yetişkin içerik mi
    val backdropPath: String,            // Arka plan resminin yolu (nullable olabilir)
    val genreIds: ArrayList<Int>,         // Türlerin ID'leri için ArrayList
    val id: Int,                          // Dizinin benzersiz kimliği
    val originCountry: ArrayList<String>, // Dizinin menşe ülkesi (birden fazla ülke olabilir)
    val originalLanguage: String,         // Dizinin orijinal dili
    val originalName: String,             // Dizinin orijinal adı
    val overview: String,                 // Dizinin açıklaması
    val popularity: Double,               // Dizinin popülerlik puanı
    val posterPath: String,              // Poster resminin yolu (nullable olabilir)?
    val firstAirDate: String,             // Dizinin ilk yayın tarihi
    val name: String,                     // Dizinin adı
    val voteAverage: Double,              // Oy ortalaması
    val voteCount: Int                    // Oy sayısı
) {
}