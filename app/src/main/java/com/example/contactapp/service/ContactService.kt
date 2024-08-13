package com.example.contactapp.service

import com.example.contactapp.ContactApp
import com.example.contactapp.data.dataBase.ContactEntity
import com.example.contactapp.data.model.ContactModel
import com.example.contactapp.data.model.Location
import com.example.contactapp.data.model.Street
import com.example.contactapp.data.network.ContactApiClient
import com.example.contactapp.data.provider.ContactProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.processNextEventInCurrentThread
import kotlinx.coroutines.withContext


class ContactService {
    private val contactProvider = ContactProvider()

    suspend fun getContacts(): List<ContactEntity> {

        //1. Chequear si hay contactos guardados en nuestra database.

        var contactsFromDatabase = getContactsFromDatabase()
        //2. Si no hay contacts, que haga un fetch desde la api y guarde los contactos


        if (contactsFromDatabase.isEmpty()) {
            val contactsFromApi = getContactsFromApi()
            saveAllContactOnDatabase(contactsFromApi)
            contactsFromDatabase = getContactsFromDatabase()
        }
        println(contactsFromDatabase)

        return withContext(Dispatchers.IO) {
            contactsFromDatabase
        }
    }

    suspend fun saveContact(contact: ContactEntity) {

        ContactApp.database.contactDao().saveContact(contact)

    }

    suspend fun saveAllContactOnDatabase(contacts: List<ContactModel>) {

        contacts.forEach { contact ->
            val newContact = ContactEntity(id = 0, name = contact.name.first, last = contact.name.last, email = contact.email,
                phone = contact.phone, picture = contact.picture.large, location = contact.location.street.name, country = contact.location.country)
            saveContact(newContact)

        }

    }

    suspend fun getContactsFromDatabase(): List<ContactEntity> {
        return ContactApp.database.contactDao().getAllContacts()
    }

    suspend fun getContactsFromApi(): List<ContactModel> {
        val response =
            contactProvider.provideRetrofit().create(ContactApiClient::class.java).getContact()
        return response.body()?.results ?: emptyList()
    }

    suspend fun updateContact(contact: ContactEntity) {
        return ContactApp.database.contactDao().updateContact(contact)
    }



}

