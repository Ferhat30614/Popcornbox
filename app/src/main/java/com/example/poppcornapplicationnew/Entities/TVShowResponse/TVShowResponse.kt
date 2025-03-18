package com.example.poppcornapplicationnew.Entities.TVShowResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("page")
    @Expose
    val page: Int,                        // Mevcut sayfa numarası
    @SerializedName("results")
    @Expose
    val results: List<TVShow>,            // Dizilerin listesi
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,                  // Toplam sayfa sayısı
    @SerializedName("total_results")
    @Expose
    val totalResults: Int                 // Toplam sonuç sayısı
)
