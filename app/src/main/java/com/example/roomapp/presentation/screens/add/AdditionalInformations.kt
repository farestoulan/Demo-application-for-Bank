package com.example.roomapp.presentation.screens.add

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
import com.example.roomapp.data.models.entitys.ClientEntity
import com.example.roomapp.data.models.entitys.User
import com.example.roomapp.R
import com.example.roomapp.presentation.viewModels.ViewModel
import com.example.roomapp.databinding.FragmentAdditionalInformationsBinding

class AdditionalInformations : Fragment() {


    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient: Int = 1

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
            ArrayAdapter(requireContext(), R.layout.dropdown_item, position)
        binding.listCreditType.setAdapter(arrayAdapter)

        aViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        nameData = AdditionalInformationsArgs.fromBundle(requireArguments()).clientName.toString()
        emailData = AdditionalInformationsArgs.fromBundle(requireArguments()).clientEmail.toString()
        passwordData = AdditionalInformationsArgs.fromBundle(requireArguments()).clientPassword.toString()

        binding.btnRegisterAdditional.setOnClickListener {

          val guserId=  insertDataToUserinDatabase()
            insertDataToDatabase(guserId)

        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    //insert Data to Client Entity
    private fun insertDataToDatabase( guserid:Long)  {

        val creditType = binding.listCreditType.text.toString()
        val job = binding.etJob.text.toString()



        if (inputCheck(creditType, job)) {
            // Create User Object
            val client = ClientEntity(0,0,"", creditType, job,guserid )
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
    private fun insertDataToUserinDatabase() :Long{


        // Create user Object
        val user = User(
            0, nameData, emailData, passwordData, isClient
        )
        // Add Data to Database
        var gUserId =  aViewModel.addUser(user)

        Toast.makeText(requireContext(), "Successfully  Client!", Toast.LENGTH_LONG).show()


       return gUserId
    }


}