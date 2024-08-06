package com.example.contactapp.view

import android.os.Bundle
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
        setCurrentFragment(contactListFragment)
        initUI()

    }


    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {

    }

    private fun initUIState() {
        contactViewModel.getContacts()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayoutFragment.id, fragment)
            commit()
        }
    }
}