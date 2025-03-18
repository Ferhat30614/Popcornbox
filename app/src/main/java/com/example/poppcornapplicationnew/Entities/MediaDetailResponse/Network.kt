package com.example.poppcornapplicationnew.Entities.MediaDetailResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Network(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("logo_path")
    @Expose
    val logoPath: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("origin_country")
    @Expose
    val originCountry: String?
) : Parcelable
