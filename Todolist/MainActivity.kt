package com.example.to_do_list
import  android.content.Context
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import java.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var selectedDate = ""
    var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextText)
        val addBtn = findViewById<Button>(R.id.button4)



        findViewById<Button>(R.id.button2).setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, "timePicker")
        }

        findViewById<Button>(R.id.pickDate).setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }

        val sharedPref = getSharedPreferences("MyTasks", MODE_PRIVATE)

        val textView = findViewById<TextView>(R.id.textView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}




class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker.
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it.
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        // Do something with the time the user picks.
        (activity as MainActivity).selectedTime = selectedTime
    }
}

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), this, year, month, day)

    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

        val selectedDate = "$day/${month + 1}/$year"

        // Do something with the date the user picks.
        (activity as MainActivity).selectedDate = selectedDate
    }
}



