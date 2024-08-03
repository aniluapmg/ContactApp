package com.example.contactapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.model.ContactModel
import com.example.contactapp.service.ContactService
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {
    private val contactService = ContactService()

    val contacts = MutableLiveData<List<ContactModel>>()

    fun getContacts() {
        viewModelScope.launch {
            val result = contactService.getcontacts()
            if (result.isNotEmpty()) {

                println("Contacts desde el viewmodel" + contacts)

                contacts.postValue(result)

            }
        }
    }
}