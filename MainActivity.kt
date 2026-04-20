package com.example.fontapp

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var txtName: EditText

    lateinit var chkUnderline: CheckBox
    lateinit var chkBold: CheckBox
    lateinit var chkItalic: CheckBox

    lateinit var rdoUpper: RadioButton
    lateinit var rdoLower: RadioButton

    lateinit var spinnerFontSize: Spinner
    lateinit var listView: ListView

    val historyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtName = findViewById(R.id.editTextText)

        chkUnderline = findViewById(R.id.chkUnderline)
        chkBold = findViewById(R.id.chkBold)
        chkItalic = findViewById(R.id.chkItalic)

        rdoUpper = findViewById(R.id.radio_upper)
        rdoLower = findViewById(R.id.radio_lower)

        spinnerFontSize = findViewById(R.id.spinnerFontSize)
        listView = findViewById(R.id.listViewStyles)

        // ✅ Checkbox listeners
        chkBold.setOnCheckedChangeListener { _, _ -> applyStyles() }
        chkItalic.setOnCheckedChangeListener { _, _ -> applyStyles() }
        chkUnderline.setOnCheckedChangeListener { _, _ -> applyStyles() }

        //Radio buttons
        rdoUpper.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                txtName.setText(txtName.text.toString().uppercase())
            }
        }

        rdoLower.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                txtName.setText(txtName.text.toString().lowercase())
            }
        }

        //Spinner setup (Font Size)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.font_size_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFontSize.adapter = adapter

        spinnerFontSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val size = parent.getItemAtPosition(position).toString().toFloat()
                txtName.textSize = size
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //ListView setup
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList)
        listView.adapter = listAdapter
    }

    fun applyStyles() {

        //Typeface logic
        val typeface = when {
            chkBold.isChecked && chkItalic.isChecked -> Typeface.BOLD_ITALIC
            chkBold.isChecked -> Typeface.BOLD
            chkItalic.isChecked -> Typeface.ITALIC
            else -> Typeface.NORMAL
        }
        txtName.setTypeface(null, typeface)

        //Underline logic
        if (chkUnderline.isChecked) {
            val spannable = SpannableString(txtName.text)
            spannable.setSpan(UnderlineSpan(), 0, spannable.length, 0)
            txtName.setText(spannable)
        } else {
            txtName.setText(txtName.text.toString())
        }

        txtName.setSelection(txtName.text.length)

        //Add to ListView (history)
        val styledText = txtName.text.toString()
        historyList.add(styledText)
        (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}
