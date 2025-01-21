package com.example.poppcornapplicationnew

data class MediaDetails (
    val adult: Boolean?,                      // İçerik yetişkinlere uygun mu?
    val backdrop_path: String?,               // Arka plan görselinin yolu
    val belongs_to_collection: BelongsToCollection?, // Film bir seriye ait mi? (Sadece filmler için)
    val budget: Int?,                         // Filmin bütçesi (Sadece filmler için)
    val original_title: String?,              // Filmin orijinal başlığı (Sadece filmler için)
    val episode_run_time: List<Int>?,         // Ortalama bölüm süresi (Sadece diziler için)
    val first_air_date: String?,              // Dizinin ilk yayın tarihi (Sadece diziler için)
    val genres: List<Genre>?,                 // Türlerin listesi (Film/Dizi için ortak)
    val id: Int?,                             // Medyanın benzersiz ID'si
    val last_air_date: String?,               // Dizinin son yayın tarihi (Sadece diziler için)
    val name: String?,                        // Dizinin adı (Sadece diziler için)
    val number_of_episodes: Int?,             // Toplam bölüm sayısı (Sadece diziler için)
    val number_of_seasons: Int?,              // Toplam sezon sayısı (Sadece diziler için)
    val origin_country: List<String>?,        // Yapımcı ülkelerin kısaltmaları (Sadece diziler için)
    val original_name: String?,               // Dizinin orijinal adı (Sadece diziler için)
    val seasons: List<Season>?,               // Sezon bilgileri (Sadece diziler için)
    val original_language: String?,           // Orijinal dil (Film/Dizi için ortak)
    val overview: String?,                    // İçerik özeti (Film/Dizi için ortak)
    val popularity: Double?,                  // Popülerlik puanı (Film/Dizi için ortak)
    val poster_path: String?,                 // Poster görselinin yolu (Film/Dizi için ortak)
    val production_companies: List<ProductionCompany>?, // Yapımcı şirketler (Film/Dizi için ortak)
    val production_countries: List<ProductionCountry>?, // Yapımcı ülkeler (Film/Dizi için ortak)
    val release_date: String?,                // Filmin vizyon tarihi (Sadece filmler için)
    val revenue: Long?,                       // Filmin hasılatı (Sadece filmler için)
    val runtime: Int?,                        // Filmin süresi (dakika) (Sadece filmler için)
    val status: String?,                      // İçerik durumu (ör. Released, Returning Series)
    val tagline: String?,                     // Slogan veya tagline (Film/Dizi için ortak)
    val title: String?,                       // Filmin başlığı (Sadece filmler için)
    val vote_average: Double?,                // Ortalama oy puanı (Film/Dizi için ortak)
    val vote_count: Int?                      // Toplam oy sayısı (Film/Dizi için ortak)
){

}