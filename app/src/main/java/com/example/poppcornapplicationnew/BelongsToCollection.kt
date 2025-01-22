package com.example.poppcornapplicationnew

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BelongsToCollection(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?
) : Parcelable
