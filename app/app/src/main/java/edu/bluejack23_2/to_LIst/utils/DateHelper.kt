package edu.bluejack23_2.to_LIst.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateHelper {
    const val dateFormatA = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    const val dateFormatB = "yyyy-MM-dd'T'HH:mm"

    fun convertStringToDate(dateString: String): LocalDate {
        var localDateTime: LocalDate
        try {
            val formatter = DateTimeFormatter.ofPattern(this.dateFormatA)
            localDateTime = LocalDate.parse(dateString, formatter)
        } catch (e: Exception) {
            val formatter = DateTimeFormatter.ofPattern(this.dateFormatB)
            localDateTime = LocalDate.parse(dateString, formatter)
        }

        return localDateTime
    }


    fun dateIsToday(dateString: String): Boolean {
        val date: LocalDate = this.convertStringToDate(dateString)
        val today = LocalDate.now()

        return today.isEqual(date)
    }

    fun dateIsInInterval(dateString: String, startDayInterval: Int, dayInterval: Int): Boolean {
        val date: LocalDate = this.convertStringToDate(dateString)
        val startDate = LocalDate.now().plusDays(startDayInterval.toLong())
        val endDate = LocalDate.now().plusDays(dayInterval.toLong())

        return (date.isEqual(startDate) || date.isEqual(endDate) ||
                (date.isAfter(startDate) && date.isBefore(endDate))
                )
    }
}