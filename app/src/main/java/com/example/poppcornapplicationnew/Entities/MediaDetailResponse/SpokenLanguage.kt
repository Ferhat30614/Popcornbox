package com.example.poppcornapplicationnew.Entities.MediaDetailResponse

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage(
    @SerializedName("english_name")
    @Expose
    val englishName: String?, // Dilin İngilizce adı

    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String?, // Dil kodu

    @SerializedName("name")
    @Expose
    val name: String? // Dilin adı
) : Parcelable
