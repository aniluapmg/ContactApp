package com.example.contactapp.view.contactDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentContactDetailBinding
import com.example.contactapp.viewModel.ContactViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso


class ContactDetailFragment : Fragment() {
    //1. Pasar este fragment a viewBinding
    //2.Instanciiar el viewModel correspondiente
    //3. Crear nuestras funciones de inicializacion

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
        binding.heartFavoriteButton.setOnClickListener {
            contactViewModel.updateContact()
        }

        binding.notFavoriteButton.setOnClickListener {
            contactViewModel.updateContact()
        }



    }

    private fun initUIState() {
        //binding.textViewFirstName.text = contactViewModel.currentContact.name
        //contactViewModel.getContacts()
        contactViewModel.currentContact.observe(viewLifecycleOwner, Observer { currentContact ->

            if (currentContact != null) {
                //Carga la imagen usando Picasso
                Picasso.get().load(currentContact.picture).into(binding.imageView)

                //Asigna los demas datos a los TextViews
                binding.textViewFirstName.text = currentContact.name + ", " + currentContact.last
                binding.textViewEmail.text = currentContact.email
                binding.textViewPhone.text = currentContact.phone
                binding.textViewAddressStreet.text =
                    currentContact.location + ", " + currentContact.country

                if (currentContact.favorite) {
                    binding.heartFavoriteButton.isVisible = true
                    binding.notFavoriteButton.isVisible = false
                }else{
                    binding.heartFavoriteButton.isVisible = false
                    binding.notFavoriteButton.isVisible = true
                }


            }

        })
    }
}




