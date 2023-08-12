package com.filipemorgado.myexpenses.utilities

import com.filipemorgado.myexpenses.model.DateRange
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    // Date filtering Section
    fun Date.isWithinFilter(dateFilter: DateRange): Boolean {
        val currentDate = Calendar.getInstance()
        return when (dateFilter) {
            DateRange.TODAY -> isSameDayAs(currentDate)
            DateRange.WEEK -> isWithinLastWeek(currentDate)
            DateRange.MONTH -> isWithinLastMonth(currentDate)
            DateRange.YEAR -> isWithinLastYear(currentDate)
        }
    }

    private fun Date.isSameDayAs(other: Calendar): Boolean {
        val cal = Calendar.getInstance().apply { time = this@isSameDayAs }
        return cal.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
    }

    private fun Date.isWithinLastWeek(currentDate: Calendar): Boolean {
        val cal = Calendar.getInstance().apply { time = this@isWithinLastWeek }
        return cal.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                cal.get(Calendar.WEEK_OF_YEAR) == currentDate.get(Calendar.WEEK_OF_YEAR)
    }

    private fun Date.isWithinLastMonth(currentDate: Calendar): Boolean {
        val cal = Calendar.getInstance().apply { time = this@isWithinLastMonth }
        return cal.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                cal.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
    }

    private fun Date.isWithinLastYear(currentDate: Calendar): Boolean {
        val cal = Calendar.getInstance().apply { time = this@isWithinLastYear }
        return cal.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)
    }
    // End of Date filtering Section

    // Date formatter
    fun formatDate(date: Date, pattern: String): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }
    // End Date formatter

}
