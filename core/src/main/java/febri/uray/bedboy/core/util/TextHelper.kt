package febri.uray.bedboy.core.util

import java.text.NumberFormat
import java.util.Locale

object TextHelper {

    fun formatRupiah(amount: String): String {
        try {
            // Convert the input amount to a Double
            val amountValue = amount.toDouble()

            // Create a NumberFormat instance for the Indonesian locale
            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

            // Remove the currency symbol and get the formatted amount
            var formattedAmount = format.format(amountValue).replace("IDR", "")
                .replace("Rp", "").trim()

            // Add the "Rp" symbol and the trailing hyphen
            formattedAmount = "Rp $formattedAmount ,-".replace(",00", "")

            return formattedAmount
        } catch (e: NumberFormatException) {
            return amount
        }
    }
}