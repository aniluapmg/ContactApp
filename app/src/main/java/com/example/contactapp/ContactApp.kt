package com.example.contactapp

import android.app.Application
import com.example.contactapp.data.dataBase.ContactDatabase
import com.example.contactapp.data.provider.ContactProvider

class ContactApp : Application() {

    private val contactProvider = ContactProvider()

    companion object {
        lateinit var  database: ContactDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = contactProvider.provideRoom(this)
    }

}