package com.example.contactapp.view.contactDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentContactDetailBinding
import com.example.contactapp.viewModel.ContactViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso


class ContactDetailFragment : Fragment() {
    private lateinit var binding: FragmentContactDetailBinding
    private val contactViewModel: ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailBinding.inflate(layoutInflater)

        initUI()
        return binding.root

    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {

    }

    private fun initUIState() {
        //contactViewModel.getContacts()
        contactViewModel.contacts.observe(viewLifecycleOwner, Observer { contacts ->
            if (contacts.isNotEmpty()) {
                //Carga la imagen usando Picasso
                Picasso.get().load(contacts[0].picture.large).into(binding.imageView)

                //Asigna los demas datos a los TextViews
                binding.textViewFirstName.text = contacts[0].name.first
                binding.textViewLastName.text = contacts[0].name.last
                binding.textViewEmail.text = contacts[0].email
                binding.textViewPhone.text = contacts[0].phone
                binding.textViewAddressStreet.text = contacts[0].location.street.name + ", " + contacts[0].location.country



            }

        })
    }
}




