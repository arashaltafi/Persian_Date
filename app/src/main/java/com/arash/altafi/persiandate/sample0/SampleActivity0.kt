package com.arash.altafi.persiandate.sample0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.persiandate.R
import com.arash.altafi.persiandate.utils.*
import kotlinx.android.synthetic.main.activity_sample0.*
import saman.zamani.persiandate.PersianDate

class SampleActivity0 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample0)

        init()
    }

    private fun init() {
        PersianDate(System.currentTimeMillis()).getDateString()
        PersianDate(System.currentTimeMillis()).getTimeString()
        PersianDate(System.currentTimeMillis()).getDateStringWithClock()
        PersianDate(System.currentTimeMillis()).monthDays
        PersianDate(System.currentTimeMillis()).minute
        PersianDate(System.currentTimeMillis()).time

        btnConvertStringTimeToMilliSecond.setOnClickListener {
            val time = "07:29".convertToUnixTime()
            Toast.makeText(this, "$time", Toast.LENGTH_SHORT).show()
        }

        btnConvertStringTimeDateToMilliSecond.setOnClickListener {
            val time =  "07:29".convertToUnixTime(true, "1402/01/18")
            Toast.makeText(this, "$time", Toast.LENGTH_SHORT).show()
        }

        btnConvertMilliSecondToString.setOnClickListener {
            val time = 1680862842343L.getPatternDoubleTime(true)
            Toast.makeText(this, time, Toast.LENGTH_SHORT).show()
        }

    }

}