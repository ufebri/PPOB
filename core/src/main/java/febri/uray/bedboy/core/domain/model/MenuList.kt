package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import android.text.InputType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuList(
    var idMenu: String,
    var nameMenu: String,
    var categoryMenu: String,
    var inputType: Int
) : Parcelable