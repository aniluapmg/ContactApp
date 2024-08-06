package com.example.contactapp.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ContactEntity::class], version = 2 )
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}