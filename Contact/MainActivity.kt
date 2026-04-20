// MainActivity.kt
package com.example.contactapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val allContacts = listOf(
        // ✅ Each contact uses R.drawable.contact_1, contact_2 etc.
        // Add your image files as:  res/drawable/contact_1.png, contact_2.png ...
        Contact("Vaastav Parikh",    "Product Manager",         "9876543210", "vaastav@email.com",  "Jan 15, 1995", R.drawable.contact_1),
        Contact("Jane Cooper",       "Web Designer",            "9123456780", "jane@email.com",      "Mar 22, 1993", R.drawable.contact_2),
        Contact("Eleanor Pena",      "Marketing Coordinator",   "9988776655", "eleanor@email.com",   "Jul 10, 1997", R.drawable.contact_3),
        Contact("Darlene Robertson", "President of Sales",      "9871234560", "darlene@email.com",   "Dec 5,  1988", R.drawable.contact_4),
        Contact("Jerome Bell",       "Nursing Assistant",       "9000112233", "jerome@email.com",    "Feb 28, 1996", R.drawable.contact_5),
        Contact("Robert Fox",        "Dog Trainer",             "9812345670", "robert@email.com",    "Sep 14, 1991", R.drawable.contact_6),
        Contact("Savannah Nguyen",   "Medical Assistant",       "9765432180", "savannah@email.com",  "Apr 3,  1994", R.drawable.contact_7),
        Contact("Jenny Wilson",      "Product Manager",         "9654321780", "jenny@email.com",     "Aug 19, 1992", R.drawable.contact_8)
    )

    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(allContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filtered = allContacts.filter {
                    it.name.lowercase().contains(query) || it.role.lowercase().contains(query)
                }
                adapter.updateList(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
