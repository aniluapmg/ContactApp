package com.example.contactapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.dataBase.ContactEntity
import com.example.contactapp.data.model.ContactModel
import com.example.contactapp.service.ContactService
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {
    private val contactService = ContactService()

    //Contacto actual
    val currentContact = MutableLiveData<ContactEntity?>(null)

    val contacts = MutableLiveData<List<ContactEntity>>()

    var contactListFromDB: List<ContactEntity> = emptyList()

    fun getContacts() {
        viewModelScope.launch {
            val result = contactService.getContacts()
            contactListFromDB = result
            if (result.isNotEmpty()) {

                println("Contacts desde el viewmodel" + contacts)

                contacts.postValue(result)

            }
        }
    }

    fun setCurrentContact(contactCurrent:ContactEntity) {
        currentContact.postValue(contactCurrent)
    }

    fun filterContactList(charSequence: CharSequence){
        println("Secuencia desde el viemodel")
        println(charSequence)

        val contactFiltered = contactListFromDB.filter {
            contact ->
            contact.name.contains(charSequence, ignoreCase = true)
                    || contact.email.contains(charSequence, ignoreCase = true)
        }

        contacts.postValue(contactFiltered)
    }

    fun resetContactList(){
        contacts.postValue(contactListFromDB)
    }

    fun updateContact() {
        viewModelScope.launch {



            val contactUpdated = currentContact.value?.copy(favorite = !currentContact.value!!.favorite)



            if (contactUpdated != null) {
                contactService.updateContact(contactUpdated)
                currentContact.postValue(contactUpdated)
            }
            //1.forma no optimizada
            //2. hacer una copia de la lista de contactos previa
            //2.2 buscar el indice del contacto a actualizar
            //2.3 actualizar dicho contacto den la copia de la lista
            //2.4 actulizar nuestro contacto en la lista que se muestra
            getContacts()


        }

        }

}