package com.example.fontapp  // change if needed

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var txtName: EditText

    lateinit var chkUnderline: CheckBox
    lateinit var chkBold: CheckBox
    lateinit var chkItalic: CheckBox

    lateinit var rdoUpper: RadioButton
    lateinit var rdoLower: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtName     = findViewById(R.id.editTextText)
        chkUnderline = findViewById(R.id.chkUnderline)
        chkBold      = findViewById(R.id.chkBold)
        chkItalic    = findViewById(R.id.chkItalic)
        rdoUpper     = findViewById(R.id.radio_upper)
        rdoLower     = findViewById(R.id.radio_lower)


        //For Checkboxes
        chkBold.setOnCheckedChangeListener      { _, _ -> applyStyles() }
        chkItalic.setOnCheckedChangeListener    { _, _ -> applyStyles() }
        chkUnderline.setOnCheckedChangeListener { _, _ -> applyStyles() }


        //For the Radio buttons

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
    }

    fun applyStyles() {

        val typeface = when {
            chkBold.isChecked && chkItalic.isChecked -> Typeface.BOLD_ITALIC
            chkBold.isChecked                        -> Typeface.BOLD
            chkItalic.isChecked                      -> Typeface.ITALIC
            else                                     -> Typeface.NORMAL
        }
        txtName.setTypeface(null, typeface)


        if (chkUnderline.isChecked) {
            val spannable = SpannableString(txtName.text)
            spannable.setSpan(UnderlineSpan(), 0, spannable.length, 0)
            txtName.setText(spannable)
            txtName.setSelection(txtName.text.length)
        } else {
            val text = txtName.text.toString()
            txtName.setText(text)
            txtName.setSelection(txtName.text.length)
        }


    }
}
