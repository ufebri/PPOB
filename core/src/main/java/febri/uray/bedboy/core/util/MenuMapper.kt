package febri.uray.bedboy.core.util

import android.text.InputType
import febri.uray.bedboy.core.domain.model.MenuList

object MenuMapper {

    val categoryMenuList = listOf("TopUp", "Entertainment", "Lain-Lain", "International", "All")

    val generateMenuList: List<MenuList> = listOf(
        MenuList("bicara", "Paket Telpon", categoryMenuList[0], InputType.TYPE_CLASS_PHONE),
        MenuList("data", "Paket Data", categoryMenuList[0], InputType.TYPE_CLASS_PHONE),
        MenuList("emeterai", "E-Meterai", categoryMenuList[2], InputType.TYPE_CLASS_NUMBER),
        MenuList("emoney", "E-Money", categoryMenuList[0], InputType.TYPE_CLASS_PHONE),
        MenuList("etoll", "E-Toll", categoryMenuList[0], InputType.TYPE_CLASS_NUMBER),
        MenuList("game", "Voucher Game", categoryMenuList[1], InputType.TYPE_CLASS_TEXT),
        MenuList(
            "international",
            "Paket International",
            categoryMenuList[3],
            InputType.TYPE_CLASS_PHONE
        ),
        MenuList("malaysia", "Malaysia", categoryMenuList[3], InputType.TYPE_CLASS_PHONE),
        MenuList("philipines", "Philipines", categoryMenuList[3], InputType.TYPE_CLASS_PHONE),
        MenuList("pln", "PLN", categoryMenuList[0], InputType.TYPE_CLASS_NUMBER),
        MenuList("pulsa", "Pulsa", categoryMenuList[0], InputType.TYPE_CLASS_PHONE),
        MenuList("roaming", "Roaming", categoryMenuList[3], InputType.TYPE_CLASS_PHONE),
        MenuList("singapore", "Singapore", categoryMenuList[3], InputType.TYPE_CLASS_PHONE),
        MenuList("streaming", "Streaming", categoryMenuList[1], InputType.TYPE_CLASS_TEXT),
        MenuList("taiwan", "Taiwan", categoryMenuList[3], InputType.TYPE_CLASS_PHONE),
        MenuList("voucher", "Voucher Belanja", categoryMenuList[1], InputType.TYPE_CLASS_TEXT)
    )
}