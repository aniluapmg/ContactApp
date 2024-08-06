package com.example.contactapp.data.provider

import android.content.Context
import androidx.room.Room
import com.example.contactapp.data.dataBase.ContactDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactProvider {

    val CONTACT_DATABASE_NAME = "contact-db"
    val RANDOMMUSER_ENDPOINT = "https://randomuser.me/api/"

    fun provideRetrofit(): Retrofit {
        val endpointUrl = RANDOMMUSER_ENDPOINT
        return Retrofit.Builder()
            .baseUrl(endpointUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun provideRoom(context: Context): ContactDatabase {
        return Room.databaseBuilder(context, ContactDatabase::class.java, CONTACT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
            .build()
    }


}