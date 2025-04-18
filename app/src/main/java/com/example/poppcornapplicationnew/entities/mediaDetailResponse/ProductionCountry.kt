package com.example.poppcornapplicationnew.entities.mediaDetailResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String?,

    @SerializedName("name")
    @Expose
    val name: String?
) : Parcelable
