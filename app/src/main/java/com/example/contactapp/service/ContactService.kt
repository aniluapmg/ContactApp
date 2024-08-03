package com.example.contactapp.service

import com.example.contactapp.data.model.ContactModel
import com.example.contactapp.data.network.ContactApiClient
import com.example.contactapp.data.provider.ContactProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class ContactService {
    private val contactProvider = ContactProvider()
    suspend fun getcontacts(): List<ContactModel> {
        return withContext(Dispatchers.IO) {
            val response = contactProvider.provideRetrofit().create(ContactApiClient::class.java).getContact()
            response.body()?.results ?: emptyList()
        }
    }
}