import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedBy(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("credit_id")
    @Expose
    val creditId: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("original_name")
    @Expose
    val originalName: String?,

    @SerializedName("gender")
    @Expose
    val gender: Int?,

    @SerializedName("profile_path")
    @Expose
    val profilePath: String?
) : Parcelable
