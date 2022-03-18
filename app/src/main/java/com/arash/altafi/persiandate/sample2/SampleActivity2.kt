package com.arash.altafi.persiandate.sample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.persiandate.R
import kotlinx.android.synthetic.main.activity_sample2.*
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.widget.Toast
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import com.xdev.arch.persiancalendar.datepicker.CalendarConstraints
import com.xdev.arch.persiancalendar.datepicker.DateValidatorPointForward
import com.xdev.arch.persiancalendar.datepicker.Month
import com.xdev.arch.persiancalendar.datepicker.calendar.PersianCalendar

class SampleActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample2)

        init()
    }

    private fun init() {
        val calendar = PersianCalendar()

        calendar.setPersian(1395, Month.FARVARDIN, 1)
        val start = calendar.timeInMillis

        calendar.setPersian(PersianCalendar.getToday().year, PersianCalendar.getToday().month, PersianCalendar.getToday().day)
        val end = calendar.timeInMillis

        val openAt = PersianCalendar.getToday().timeInMillis

        val constraints = CalendarConstraints.Builder()
            .setStart(start)
            .setEnd(end)
            .setOpenAt(openAt)
            .setValidator(DateValidatorPointForward.from(start))
            .build()

        val rangePicker = com.xdev.arch.persiancalendar.datepicker.MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("تاریخ را انتخاب کنید")
            .setCalendarConstraints(constraints)
            .build()

        rangePicker.addOnPositiveButtonClickListener {
            val first = PersianCalendar(it?.first!!)
            val second = PersianCalendar(it.second!!)
            Toast.makeText(this , "first: $first" , Toast.LENGTH_SHORT).show()
            Toast.makeText(this , "second: $second" , Toast.LENGTH_SHORT).show()
        }

        btn_date_range.setOnClickListener {
            rangePicker.show(supportFragmentManager, "RangePickerTag")
        }

    }

}