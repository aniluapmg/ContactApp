package com.example.contactapp.data.provider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactProvider {
    fun provideRetrofit(): Retrofit {
        val endpointUrl = "https://randomuser.me/api/?results=20&exc=login,registered,dob,id&noinfo"
        return Retrofit.Builder()
            .baseUrl(endpointUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}