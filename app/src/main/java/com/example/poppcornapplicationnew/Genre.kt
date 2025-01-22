package com.example.poppcornapplicationnew

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?
) : Parcelable
