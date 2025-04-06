package com.example.poppcornapplicationnew.entities.movieResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("page")
    @Expose
    var page:Int,
    @SerializedName("results")
    @Expose
    var results:List<Movie>,
    @SerializedName("total_pages")
    @Expose
    var total_pages:Int,
    @SerializedName("total_results")
    @Expose
    var total_results:Int) {
}