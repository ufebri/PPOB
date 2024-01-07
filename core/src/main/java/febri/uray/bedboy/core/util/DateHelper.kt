package febri.uray.bedboy.core.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateHelper {

    fun getCurrentFormattedDate(): String {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

        return formatter.format(calendar.time)
    }
}