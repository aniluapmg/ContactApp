package com.example.contactapp.view.contactList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.data.dataBase.ContactEntity
import com.example.contactapp.databinding.ItemContactListBinding

class ContactListAdapter(
    private var contacts: List<ContactEntity>
) : RecyclerView.Adapter<ContactListAdapter.ContactHolder>() {

    inner class ContactHolder(val binding: ItemContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun render(contact: ContactEntity) {
                binding.textViewName.text = contact.name
                binding.textViewEmail.text = contact.email
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val binding =
            ItemContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding)


    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
       holder.render(contacts.get(position))
    }

}
