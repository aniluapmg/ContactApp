package com.example.contactapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityContactBinding
import com.example.contactapp.view.contactList.ContactListFragment
import com.example.contactapp.viewModel.ContactViewModel
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.contactapp.data.dataBase.ContactEntity
import com.example.contactapp.view.contactDetail.ContactDetailFragment

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private val contactListFragment = ContactListFragment()
    private val contactDetailFragment = ContactDetailFragment()
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }


    private fun initUI() {
        //Iniciar los estados iniciales de la activity
        initUIState()
        //Inicializar los observables o listeners (observables que estaran escuchando
        // los eventos de la app. Ejemplo:un click en un boton)
        initUIListener()
    }

    private fun initUIListener() {
        binding.imageViewSearch.setOnClickListener {
            binding.editTextSearch.isVisible = true
            binding.textViewTitle.isVisible = false
        }

        //Nuestro segundo observable
        contactViewModel.currentContact.observe(this, Observer { currentContact ->

            //Chequear que se imprime
            println("Current contact")
            println(currentContact)

            //cada vez que se cambie el currentContact y este no sea null, que se envie al detailcontactfragment
            if (currentContact != null) {
                startDetailContactFragment(currentContact)
            }
        })

        binding.imageViewBack.setOnClickListener{
            startContactListFragment()
            contactViewModel.resetContactList()

        }

        binding.editTextSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    contactViewModel.filterContactList(s)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


    private fun startDetailContactFragment(currentContact: ContactEntity) {
        setCurrentFragment(contactDetailFragment)
        binding.textViewTitle.text = currentContact.name
        binding.imageViewBack.isVisible = true
    }

    private fun initUIState() {
        contactViewModel.getContacts()
        startContactListFragment()
    }

    private fun showTitleBar() {
        binding.textViewTitle.isVisible = true
        binding.textViewTitle.isVisible = false
    }

    private fun startContactListFragment() {
        //iniciar el fragment con la lista de contactos
        setCurrentFragment(contactListFragment)
        binding.imageViewBack.isVisible = false
        contactViewModel.resetContactList()

        //Mostrar la barra del titulo del contacto
    }

    private fun showSearchBar() {
        binding.textViewTitle.isVisible = false
        binding.editTextSearch.isVisible = true
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayoutFragment.id, fragment)
            commit()
        }
    }
}