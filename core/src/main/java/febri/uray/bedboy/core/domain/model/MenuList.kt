package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuList(
    var idMenu: String,
    var nameMenu: String,
    var categoryMenu: String
) : Parcelable

val generateMenuList: List<MenuList> = listOf(
    MenuList("bicara", "Paket Telpon",  "TopUp"),
    MenuList("data", "Paket Data",  "TopUp"),
    MenuList("emeterai", "E-Meterai",  "Lain-lain"),
    MenuList("emoney", "E-Money",  "TopUp"),
    MenuList("etoll", "E-Toll",  "TopUp"),
    MenuList("game", "Voucher Game",  "Entertainment"),
    MenuList("international", "Paket International",  "International"),
    MenuList("malaysia", "Malaysia",  "International"),
    MenuList("philipines", "Philipines",  "International"),
    MenuList("pln", "PLN",  "TopUp"),
    MenuList("pulsa", "Pulsa",  "TopUp"),
    MenuList("roaming", "Roaming",  "International"),
    MenuList("singapore", "Singapore",  "International"),
    MenuList("streaming", "Streaming",  "Entertainment"),
    MenuList("taiwan", "Taiwan",  "International"),
    MenuList("voucher", "Voucher Belanja",  "Entertainment")
)