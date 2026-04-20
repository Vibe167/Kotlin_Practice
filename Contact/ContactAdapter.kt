// ContactAdapter.kt
package com.example.contactapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private var contacts: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)   // ✅ ImageView
        val tvName: TextView    = itemView.findViewById(R.id.tvName)
        val tvRole: TextView    = itemView.findViewById(R.id.tvRole)
        val ivCall: ImageView   = itemView.findViewById(R.id.ivCall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        // ✅ Set the image from drawable resource
        holder.ivAvatar.setImageResource(contact.imageResId)

        holder.tvName.text = contact.name
        holder.tvRole.text = contact.role

        // Click row → open detail screen
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("NAME", contact.name)
                putExtra("ROLE", contact.role)
                putExtra("PHONE", contact.phone)
                putExtra("EMAIL", contact.email)
                putExtra("BIRTHDAY", contact.birthday)
                putExtra("IMAGE_RES_ID", contact.imageResId)  // ✅ pass image id
            }
            holder.itemView.context.startActivity(intent)
        }

        // Call icon
        holder.ivCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:${contact.phone}")
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = contacts.size

    fun updateList(newList: List<Contact>) {
        contacts = newList
        notifyDataSetChanged()
    }
}
