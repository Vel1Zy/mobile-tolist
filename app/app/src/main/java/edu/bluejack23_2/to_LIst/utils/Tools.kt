package edu.bluejack23_2.to_LIst.utils

import android.icu.text.SimpleDateFormat
import java.util.Date

class Tools {


    companion object {
        fun convertLongtoTime(time: Long): String {
            val date = Date(time)
            val format = java.text.SimpleDateFormat("dd MMM yyyy")
            return format.format(date)
        }
    }

}