package com.example.poppcornapplicationnew.Entities.MediaDetailResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastEpisodeToAir(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int?,

    @SerializedName("air_date")
    @Expose
    val airDate: String?,

    @SerializedName("episode_number")
    @Expose
    val episodeNumber: Int?,

    @SerializedName("episode_type")
    @Expose
    val episodeType: String?,

    @SerializedName("production_code")
    @Expose
    val productionCode: String?,

    @SerializedName("runtime")
    @Expose
    val runtime: Int?,

    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int?,

    @SerializedName("show_id")
    @Expose
    val showId: Int?,

    @SerializedName("still_path")
    @Expose
    val stillPath: String?
) : Parcelable
