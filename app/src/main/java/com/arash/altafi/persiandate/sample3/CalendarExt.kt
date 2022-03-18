package com.arash.altafi.persiandate.sample3

import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.persiandate.R
import com.xdev.arch.persiancalendar.datepicker.CalendarConstraints
import com.xdev.arch.persiancalendar.datepicker.DateValidatorPointForward
import com.xdev.arch.persiancalendar.datepicker.MaterialDatePicker
import com.xdev.arch.persiancalendar.datepicker.Month
import com.xdev.arch.persiancalendar.datepicker.calendar.PersianCalendar
import saman.zamani.persiandate.PersianDate

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