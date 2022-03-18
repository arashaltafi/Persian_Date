package com.arash.altafi.persiandate.sample1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arash.altafi.persiandate.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sample1.*
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.util.*

class SampleActivity1 : AppCompatActivity() {

    private var pattern = "l j F Y \n H:i:s"
    private val displayFormat = arrayOf(
        "فرمت نمایش را انتخاب کنید",
        "Y/m/d",
        "l j F Y \n H:i:s",
        "j F y",
        "z روز از سال",
        "s",
        "H:i",
        "l w:i:s"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        init()

        val persianDate : PersianDate = PersianDate()
        Log.i("test123321", "shyear => ${persianDate.shYear}")
        Log.i("test123321", "shMonth => ${persianDate.shMonth}")
        Log.i("test123321", "shDay =>  ${persianDate.shDay}")
        Log.i("test123321", "hour =>   ${persianDate.hour}")
        Log.i("test123321", "minute => ${persianDate.minute}")
        Log.i("test123321", "second => ${persianDate.second}")

    }

    private fun init() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                changeTime()
            }
        }, 0, 1000)

        adapter()
    }

    private fun adapter() {
        val adapter: ArrayAdapter<String> = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayFormat) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                if (position == 0) {
                    txt_date.setTextColor(Color.parseColor("#c2c2c2"))
                } else {
                    txt_date.setTextColor(Color.BLACK)
                }
                return txt_date
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
                return super.getDropDownView(position, convertView, parent)
            }

        }

        spn_format.adapter = adapter
        spn_format.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                if (position != 0) {
                    pattern = displayFormat[position]
                    changeTime()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun changeTime() {
        Handler(mainLooper).postDelayed({
            txt_date.text = number2persian(PersianDateFormat(pattern).format(PersianDate()))
        },0)
    }

    private fun number2persian(text: String): String {
        var text : String = text
        text = text.replace("0".toRegex(), "۰")
        text = text.replace("1".toRegex(), "۱")
        text = text.replace("2".toRegex(), "۲")
        text = text.replace("3".toRegex(), "۳")
        text = text.replace("4".toRegex(), "۴")
        text = text.replace("5".toRegex(), "۵")
        text = text.replace("6".toRegex(), "۶")
        text = text.replace("7".toRegex(), "۷")
        text = text.replace("8".toRegex(), "۸")
        text = text.replace("9".toRegex(), "۹")
        return text
    }

}