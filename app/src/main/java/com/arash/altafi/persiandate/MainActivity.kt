package com.arash.altafi.persiandate

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arash.altafi.persiandate.sample1.SampleActivity1
import com.arash.altafi.persiandate.sample2.SampleActivity2
import com.arash.altafi.persiandate.sample3.SampleActivity3
import kotlinx.android.synthetic.main.activity_main.*
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        btn_sample_1.setOnClickListener {
            startActivity(Intent(this , SampleActivity1::class.java))
        }
        btn_sample_2.setOnClickListener {
            startActivity(Intent(this , SampleActivity2::class.java))
        }
        btn_sample_3.setOnClickListener {
            startActivity(Intent(this , SampleActivity3::class.java))
        }
    }

}