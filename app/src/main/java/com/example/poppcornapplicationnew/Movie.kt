package com.example.poppcornapplicationnew

data class Movie (
    val adult: Boolean,                  // Filmin yetişkinlere yönelik olup olmadığı
    val backdropPath: String,           // Arka plan resminin yolu  nullable
    val genreIds: ArrayList<Int>,             // Filmin türlerinin ID'leri
    val id: Int,                          // Filmin benzersiz kimliği
    val originalLanguage: String,        // Filmin orijinal dili
    val originalTitle: String,           // Filmin orijinal başlığı
    val overview: String,                // Filmin özeti
    val popularity: Double,              // Filmin popülerlik puanı, daha hassas değer için Double kullanılır
    val posterPath: String,             // Filmin posterinin yolu  nullable
    val releaseDate: String,             // Filmin vizyon tarihi
    val title: String,                   // Filmin başlığı
    val video: Boolean,                  // Videonun olup olmadığı
    val voteAverage: Double,             // Ortalamada verilen oy puanı, hassasiyet için Double
    val voteCount: Int
){
}