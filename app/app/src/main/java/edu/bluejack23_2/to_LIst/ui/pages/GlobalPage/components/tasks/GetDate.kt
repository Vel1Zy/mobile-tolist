package edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.tasks

import java.time.LocalDateTime
import java.util.Calendar

fun getCurrentDateWithTemplate(): String{
    val tempcalendar = Calendar.getInstance()

    val newDate = LocalDateTime.of(
        tempcalendar.get(Calendar.YEAR),
        tempcalendar.get(Calendar.MONTH) + 1,
        tempcalendar.get(Calendar.DAY_OF_MONTH),
        tempcalendar.get(Calendar.HOUR_OF_DAY),
        tempcalendar.get(Calendar.MINUTE),
        tempcalendar.get(Calendar.SECOND),
        tempcalendar.get(Calendar.MILLISECOND) * 1_000_000
    ).atZone(tempcalendar.timeZone.toZoneId())
        .toLocalDateTime()

    val date = newDate.toString()
    return date
}