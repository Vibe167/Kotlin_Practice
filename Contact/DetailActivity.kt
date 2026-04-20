// DetailActivity.kt
package com.example.contactapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name       = intent.getStringExtra("NAME") ?: ""
        val role       = intent.getStringExtra("ROLE") ?: ""
        val phone      = intent.getStringExtra("PHONE") ?: ""
        val email      = intent.getStringExtra("EMAIL") ?: ""
        val birthday   = intent.getStringExtra("BIRTHDAY") ?: ""
        val imageResId = intent.getIntExtra("IMAGE_RES_ID", R.drawable.contact_placeholder)

        // ✅ Set the contact photo in ImageView
        val ivDetailAvatar = findViewById<ImageView>(R.id.ivDetailAvatar)
        ivDetailAvatar.setImageResource(imageResId)

        findViewById<TextView>(R.id.tvDetailName).text     = name
        findViewById<TextView>(R.id.tvDetailPhone).text    = phone
        findViewById<TextView>(R.id.tvDetailEmail).text    = email
        findViewById<TextView>(R.id.tvDetailBirthday).text = birthday
        findViewById<TextView>(R.id.tvDetailRole).text     = role

        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }
    }
}
