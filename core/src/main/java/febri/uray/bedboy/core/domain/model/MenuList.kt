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

val generateMenuList: List<MenuList> = listOf(
    MenuList("bicara", "Paket Telpon", "TopUp", InputType.TYPE_CLASS_PHONE),
    MenuList("data", "Paket Data", "TopUp", InputType.TYPE_CLASS_PHONE),
    MenuList("emeterai", "E-Meterai", "Lain-lain", InputType.TYPE_CLASS_NUMBER),
    MenuList("emoney", "E-Money", "TopUp", InputType.TYPE_CLASS_PHONE),
    MenuList("etoll", "E-Toll", "TopUp", InputType.TYPE_CLASS_NUMBER),
    MenuList("game", "Voucher Game", "Entertainment", InputType.TYPE_CLASS_TEXT),
    MenuList("international", "Paket International", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("malaysia", "Malaysia", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("philipines", "Philipines", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("pln", "PLN", "TopUp", InputType.TYPE_CLASS_NUMBER),
    MenuList("pulsa", "Pulsa", "TopUp", InputType.TYPE_CLASS_PHONE),
    MenuList("roaming", "Roaming", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("singapore", "Singapore", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("streaming", "Streaming", "Entertainment", InputType.TYPE_CLASS_TEXT),
    MenuList("taiwan", "Taiwan", "International", InputType.TYPE_CLASS_PHONE),
    MenuList("voucher", "Voucher Belanja", "Entertainment", InputType.TYPE_CLASS_TEXT)
)