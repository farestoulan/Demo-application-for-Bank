package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.ClientEntity
import com.example.roomapp.data.Employee
import com.example.roomapp.data.User
import com.example.roomapp.data.ViewModel
import com.example.roomapp.databinding.FragmentAdditionalInformationsBinding
import com.example.roomapp.databinding.FragmentPersonalInformationBinding

class AdditionalInformations : Fragment() {

    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient: Boolean = true
    private lateinit var aViewModel: ViewModel


    private var _binding: FragmentAdditionalInformationsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdditionalInformationsBinding.inflate(inflater, container, false)
        val view = binding.root

        val position = resources.getStringArray(R.array.creditType)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item_additional, position)
        binding.listCreditType.setAdapter(arrayAdapter)

        aViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        nameData = AdditionalInformationsArgs.fromBundle(requireArguments()).clientName.toString()
        emailData = AdditionalInformationsArgs.fromBundle(requireArguments()).clientEmail.toString()
        passwordData =
            AdditionalInformationsArgs.fromBundle(requireArguments()).clientPassword.toString()

        binding.btnRegisterAdditional.setOnClickListener {
            insertDataToUserinDatabase()
            insertDataToDatabase()

        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    //insert Data to User Entity
    private fun insertDataToDatabase() {
        val creditType = binding.listCreditType.text.toString()
        val job = binding.etJob.text.toString()
        if (inputCheck(creditType, job)) {
            // Create User Object
            val client = ClientEntity(
                0,
                creditType, job
            )
            // Add Data to Database
            aViewModel.addAdditionalInformation(client)
            Toast.makeText(requireContext(), "Successfully login!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_additionalInformations_to_logInFragment)
        } else {
            Toast.makeText(requireContext(), "Invalid.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(

        creditType: String,
        job: String
    ): Boolean {
        return !(TextUtils.isEmpty(creditType) && TextUtils.isEmpty(job))
    }


    //insert Data to User Entity
    private fun insertDataToUserinDatabase() {

        // Create user Object
        val user = User(
            0, nameData, emailData, passwordData, isClient
        )
        // Add Data to Database
        aViewModel.addUser(user)
        Toast.makeText(requireContext(), "Successfully login Client!", Toast.LENGTH_LONG).show()


    }


}