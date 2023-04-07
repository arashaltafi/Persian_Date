package com.arash.altafi.persiandate.utils

import androidx.appcompat.app.AppCompatActivity
import com.xdev.arch.persiancalendar.datepicker.CalendarConstraints
import com.xdev.arch.persiancalendar.datepicker.DateValidatorPointForward
import com.xdev.arch.persiancalendar.datepicker.MaterialDatePicker
import com.xdev.arch.persiancalendar.datepicker.Month
import com.xdev.arch.persiancalendar.datepicker.calendar.PersianCalendar
import saman.zamani.persiandate.PersianDate
import java.util.*
import java.util.concurrent.TimeUnit

fun PersianDate.getDateString(): String {
    val year = shYear
    val month = if (shMonth < 10) "0${shMonth}" else shMonth
    val day = if (shDay < 10) "0${shDay}" else shDay

    return ("$year/$month/$day")
}

fun PersianDate.getDateStringWithClock(withSecond: Boolean = false): String {
    val year = shYear
    val month = if (shMonth < 10) "0${shMonth}" else shMonth
    val day = if (shDay < 10) "0${shDay}" else shDay

    val secondString: String = if (withSecond) ":$second" else ""
    return ("$year/$month/$day $hour:$minute$secondString")
}

fun PersianDate.getTimeString(withSecond: Boolean = false): String {
    val secondString: String = if (withSecond) ":$second" else ""
    return ("$hour:$minute$secondString")
}

fun AppCompatActivity.showCalendarDialog(
    fromNow: Boolean = false,
    listener: (timestamp: Long?) -> Unit
) {
    val calendar = PersianCalendar()
    calendar.setPersian(1358, Month.FARVARDIN, 1)
    val start = calendar.timeInMillis
    calendar.setPersian(1500, Month.ESFAND, 29)
    val end = calendar.timeInMillis

    val openAt = PersianCalendar.getToday().timeInMillis
    val constraints = CalendarConstraints.Builder()
        .setStart(if (fromNow) openAt else start)
        .setEnd(end)
        .setOpenAt(openAt)

    if (fromNow)
        constraints.setValidator(DateValidatorPointForward.from(openAt))

    MaterialDatePicker.Builder
        .datePicker()
        .setTitleText("تاریخ انتخاب")
        .setCalendarConstraints(constraints.build()).build().apply {
            addOnPositiveButtonClickListener {
                listener(selection)
            }
        }
        .show(supportFragmentManager, "MaterialDatePicker")
}

fun Long.getPatternDoubleTime(withSecond: Boolean = false): String {
    return if (withSecond) {
        String.format(
            "%02d:%02d:%02d",
            PersianDate(this).hour,
            PersianDate(this).minute,
            PersianDate(this).second
        )
    } else {
        String.format(
            "%02d:%02d",
            PersianDate(this).hour,
            PersianDate(this).minute
        )
    }
}


fun Long.convertDurationToTime(): String {
    val convertHours = java.lang.String.format(
        "%02d", TimeUnit.MILLISECONDS.toHours(this)
    )
    val convertMinutes = java.lang.String.format(
        "%02d", TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(this)
        )
    )
    val convertSeconds = java.lang.String.format(
        "%02d", TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(this)
        )
    )
    return if (this > 3600000) "$convertHours:$convertMinutes:$convertSeconds" else "$convertMinutes:$convertSeconds"
}

fun String.convertToUnixTime(withDate: Boolean = false, date: String? = null): Long {
    if (withDate) {
        val timeParts = this.split(":")
        val dayParts = date?.split("/")
        val hour = timeParts[0]
        val minute = timeParts[1]
        val day = dayParts?.get(2)
        val month = dayParts?.get(1)
        val year = dayParts?.get(0)
        val persianCalendar = PersianCalendar()
        if (month != null && year != null && day != null)
            persianCalendar.setPersian(year.toInt(), month.toInt() - 1, day.toInt())
        hour.let { persianCalendar.set(Calendar.HOUR_OF_DAY, it.toInt()) }
        minute.let { persianCalendar.set(Calendar.MINUTE, it.toInt()) }
        persianCalendar.set(Calendar.SECOND, 0)
        return persianCalendar.timeInMillis
    } else {
        val timeParts = this.split(":")
        val hour = timeParts[0]
        val minute = timeParts[1]
        val persianCalendar = PersianCalendar()
        hour.let { persianCalendar.set(Calendar.HOUR_OF_DAY, it.toInt()) }
        minute.let { persianCalendar.set(Calendar.MINUTE, it.toInt()) }
        persianCalendar.set(Calendar.SECOND, 0)
        return persianCalendar.timeInMillis
    }
}