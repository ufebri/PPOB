package febri.uray.bedboy.tryingkotlin.RecyclerView

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemFootballClub(val name: String?, val image: Int?) : Parcelable
