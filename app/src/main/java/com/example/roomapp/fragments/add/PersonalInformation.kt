package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.entitys.Employee
import com.example.roomapp.entitys.User
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentPersonalInformationBinding


class PersonalInformation : Fragment() {
    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient: Boolean = false


    private lateinit var pViewModel: ViewModel


    private var _binding: FragmentPersonalInformationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        nameData = PersonalInformationArgs.fromBundle(requireArguments()).name.toString()
        emailData = PersonalInformationArgs.fromBundle(requireArguments()).email.toString()
        passwordData = PersonalInformationArgs.fromBundle(requireArguments()).password.toString()


        pViewModel = ViewModelProvider(this).get(ViewModel::class.java)


        val position = resources.getStringArray(R.array.Positions)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item_personal, position)
        binding.listPosition.setAdapter(arrayAdapter)

        binding.btnRegisterPersonal.setOnClickListener {
            val guserId = insertDataToUserinDatabase()
            insertDataToEmployeeinDatabase(guserId)


        }

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    //insert Data to Employee Entity
    private fun insertDataToEmployeeinDatabase(guserid: Long) {

        val position = binding.listPosition.text.toString()
        val experience = binding.etExperience.text.toString()



        if (inputCheck(position, experience)) {
            // Create employee Object
            val employee = Employee(
                0, position, experience, guserid
            )
            // Add Data to Database
            pViewModel.addPersonalInformation(employee)
            Toast.makeText(requireContext(), "Successfully login!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_personalInformation_to_logInFragment)
        } else {
            Toast.makeText(requireContext(), "Invalid.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        position: String,
        experience: String
    ): Boolean {
        return !(TextUtils.isEmpty(position) && TextUtils.isEmpty(experience))
    }


    //insert Data to User Entity
    private fun insertDataToUserinDatabase(): Long {

        // Create user Object
        val user = User(
            0, nameData, emailData, passwordData, isClient
        )
        // Add Data to Database
        var gUserId = pViewModel.addUser(user)
        Toast.makeText(requireContext(), "Successfully login Employee!", Toast.LENGTH_LONG).show()


        return gUserId
    }

//    private fun validationExperince():String?{
//        val positionText= binding.listPosition.text.toString()
//        val experince =binding.etExperience
//        if (){}
//
//       return null
//    }
}






