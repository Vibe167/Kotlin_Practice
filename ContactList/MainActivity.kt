package com.example.contactlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactList: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        contactList = arrayListOf(
            Contact("Rutuja", "9876543210", R.drawable.contact_4+),
            Contact("Vaishnavi", "9123456780", R.drawable.contact_3),
            Contact("Charles", "9988776655", R.drawable.contact_1),
            Contact("Kimi", "9988775655", R.drawable.contact_2)
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(contactList)
    }
}
