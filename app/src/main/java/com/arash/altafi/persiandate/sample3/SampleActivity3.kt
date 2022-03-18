package com.arash.altafi.persiandate.sample3

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.persiandate.R
import com.xdev.arch.persiancalendar.datepicker.CalendarConstraints
import com.xdev.arch.persiancalendar.datepicker.DateValidatorPointForward
import com.xdev.arch.persiancalendar.datepicker.MaterialDatePicker
import com.xdev.arch.persiancalendar.datepicker.Month
import com.xdev.arch.persiancalendar.datepicker.calendar.PersianCalendar
import kotlinx.android.synthetic.main.activity_sample3.*
import saman.zamani.persiandate.PersianDate

class SampleActivity3 : AppCompatActivity() {

    private var onTime: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample3)

        init()
    }

    private fun init() {
        etDate.editText?.setOnClickListener {
            this.showCalendarDialog {
                if (it != null) {
                    val pd = PersianDate(onTime)
                    TimePickerDialog(
                        this,
                        { _, hourOfDay, minute ->
                            onTime = PersianDate(it).time + (hourOfDay * 3600000L) + (minute * 60000L)
                            PersianDate(it).time
                            etDate.editText?.setText(PersianDate(onTime).getDateString())
                            etTime.editText?.setText(PersianDate(onTime).getTimeString())
                        },
                        pd.hour,
                        pd.minute,
                        true
                    ).show()
                }
            }
        }

        etTime.editText?.setOnClickListener {
            val pd = PersianDate(onTime)
            TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    onTime = PersianDate(onTime).time + (hourOfDay * 3600000L) + (minute * 60000L)
                    etTime.editText?.setText(PersianDate(onTime).getTimeString())
                },
                pd.hour,
                pd.minute,
                true
            ).show()
        }
    }

}