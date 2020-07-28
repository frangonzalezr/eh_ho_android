package io.keepcoding.eh_ho

import java.util.*

data class Topic(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String,
    val date: Date = Date(),
    val posts: Int = 0,
    val views: Int = 0
) {
    val MINUTE_MILLIS = 1000L * 60
    val HOUR_MILLIS = MINUTE_MILLIS * 60
    val DAY_MILLIS = HOUR_MILLIS * 24
    val MONTH_MILLIS = DAY_MILLIS * 30
    val YEAR_MILLIS = MONTH_MILLIS * 12

    data class TimeOffset(val amount: Int, val unit: Int)

    /**
     * Fecha de creación de topico '01/01/2020 10:00:00'
     * @param Date Fecha de consulta '01/01/2020 11:00:00'
     * @return { unit: "Hora", amount: 1 }
     **/
    fun getTimeOffset(dateToCompare: Date = Date()) : TimeOffset {
        val current = dateToCompare.time
        val diff = current - this.date.time

        val years = diff / YEAR_MILLIS
        if (years > 0) return TimeOffset(years.toInt(), Calendar.YEAR)

        val months = diff / MONTH_MILLIS
        if (months > 0) return TimeOffset(months.toInt(), Calendar.MONTH)

        val days = diff / DAY_MILLIS
        if (days > 0) return TimeOffset(days.toInt(), Calendar.DAY_OF_MONTH)

        val hours = diff / HOUR_MILLIS
        if (hours > 0) return TimeOffset(hours.toInt(), Calendar.HOUR)

        val minutes = diff / MINUTE_MILLIS
        if (minutes > 0) return TimeOffset(minutes.toInt(), Calendar.MINUTE)

        return TimeOffset(0, Calendar.MINUTE)
    }
}