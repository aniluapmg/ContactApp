package com.example.contactapp.view.contactList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.example.contactapp.R
import com.example.contactapp.databinding.FragmentContactListBinding
import com.example.contactapp.viewModel.ContactViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

class ContactListFragment : Fragment() {
    private lateinit var binding: FragmentContactListBinding
    private val contactViewModel: ContactViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentContactListBinding.inflate(layoutInflater)

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
        contactViewModel.contacts.observe(viewLifecycleOwner, Observer { contacts ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = ContactListAdapter(contacts)
        })
    }
}


