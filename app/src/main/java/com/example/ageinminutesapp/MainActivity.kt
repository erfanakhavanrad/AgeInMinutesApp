package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var txt1: TextView
    lateinit var textView6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt1 = findViewById(R.id.textView4)
        textView6 = findViewById(R.id.textView6)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { view ->
            clickDatePicker(view)
        }


    }

    private fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
      val dpd =  DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
//            Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                txt1.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate?.time?.div(60000)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time.div(60000)
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes!!
                textView6.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = Date().time - 86400000
    dpd.show()
    }
}