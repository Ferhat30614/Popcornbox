package com.example.poppcornapplicationnew

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    @SerializedName("air_date")
    @Expose
    val airDate: String?,

    @SerializedName("episode_count")
    @Expose
    val episodeCount: Int?,

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,

    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int?
) : Parcelable
