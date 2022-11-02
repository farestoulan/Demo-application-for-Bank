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
import com.example.roomapp.databinding.FragmentManagerDirectorBinding
import com.example.roomapp.data.models.entitys.User
import com.example.roomapp.presentation.viewModels.ViewModel


class ManagerDirectorFragment : Fragment() {
    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient :Int = 3
    private lateinit var pViewModel: ViewModel

    private var _binding:FragmentManagerDirectorBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentManagerDirectorBinding .inflate(inflater, container, false)
        val view =binding.root
        pViewModel = ViewModelProvider(this)[ViewModel::class.java]

        nameData = ManagerDirectorFragmentArgs.fromBundle(requireArguments()).nameManager.toString()
        emailData = ManagerDirectorFragmentArgs.fromBundle(requireArguments()). emailManager.toString()
        passwordData = ManagerDirectorFragmentArgs.fromBundle(requireArguments()).passwordManager.toString()


        binding.submitBoss.setOnClickListener {
            insertDataToUserInDatabase()
            findNavController().navigate(R.id.action_managerDirectorFragment2_to_logInFragment)
        }
        return view

    }
    //insert Data to User Entity
    private fun insertDataToUserInDatabase(){

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
        _binding = null
    }


}