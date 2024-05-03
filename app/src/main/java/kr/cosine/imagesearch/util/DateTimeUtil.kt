package kr.cosine.imagesearch.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun toFormattedDateTime(dateTimeText: String): String {
        val localDateTime = LocalDateTime.parse(dateTimeText, DateTimeFormatter.ISO_DATE_TIME)
        return formatter.format(localDateTime)
    }
}