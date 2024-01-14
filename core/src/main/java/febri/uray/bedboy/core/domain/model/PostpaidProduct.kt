package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostpaidProduct(
    var code: String,
    var name: String,
    var status: Int,
    var fee: Int,
    var komisi: Int,
    var type: String,
    var category: String
) : Parcelable
