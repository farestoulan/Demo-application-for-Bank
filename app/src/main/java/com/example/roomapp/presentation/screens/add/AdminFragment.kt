package com.example.roomapp.presentation.screens.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentAdminBinding
import com.example.roomapp.data.models.entitys.User
import com.example.roomapp.presentation.viewModels.ViewModel


class AdminFragment : Fragment() {
    private lateinit var pViewModel: ViewModel
    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient :Int = 2
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        pViewModel = ViewModelProvider(this)[ViewModel::class.java]

        nameData = AdminFragmentArgs.fromBundle(requireArguments()).nameAdmin.toString()
        emailData = AdminFragmentArgs.fromBundle(requireArguments()).emailAdmin.toString()
        passwordData = AdminFragmentArgs.fromBundle(requireArguments()).passwordAdmin.toString()

        binding.submitAdmin.setOnClickListener {
            insertDataToUserinDatabase()
            findNavController().navigate(R.id.action_adminFragment_to_logInFragment)
        }

        return  view
    }
    //insert Data to User Entity
    private fun insertDataToUserinDatabase(){

        // Create user Object
        val user = User(
            0, nameData, emailData, passwordData,isClient
        )
        // Add Data to Database
         pViewModel.addUser(user)
        Toast.makeText(requireContext(), "Successfully Register !", Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }




}