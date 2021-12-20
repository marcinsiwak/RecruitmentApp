package pl.msiwak.recruitmentapp.util.helpers

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    private const val PATTERN_DATE = "yyyy-MM-dd"
    private const val PATTERN_MODIFIED_DATE = "dd.MM.yyyy"

    fun parseDate(date: String?): String {
        if (date != null && date.isNotEmpty()) {
            var format: DateFormat = SimpleDateFormat(PATTERN_DATE, Locale.getDefault())
            try {
                val parsedDate = format.parse(date)
                format = SimpleDateFormat(PATTERN_MODIFIED_DATE, Locale.getDefault())
                return format.format(parsedDate)
            } catch (e: ParseException) {

            }

        }
        return ""
    }

}