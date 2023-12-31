package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuList(
    var idMenu: String,
    var nameMenu: String
) : Parcelable