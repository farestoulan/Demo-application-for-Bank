package com.example.roomapp.presentation.screens.add

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
import com.example.roomapp.data.models.entitys.Employee
import com.example.roomapp.data.models.entitys.User
import com.example.roomapp.presentation.viewModels.ViewModel
import com.example.roomapp.databinding.FragmentPersonalInformationBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class PersonalInformation : Fragment() {
    var isActivation: Int = 0
    var nameData: String = ""
    var emailData: String = ""
    var passwordData: String = ""
    var isClient: Int = 0
    private lateinit var pViewModel: ViewModel

    private var _binding: FragmentPersonalInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        nameData = PersonalInformationArgs.fromBundle(requireArguments()).name.toString()
        emailData = PersonalInformationArgs.fromBundle(requireArguments()).email.toString()
        passwordData = PersonalInformationArgs.fromBundle(requireArguments()).password.toString()

        pViewModel = ViewModelProvider(this)[ViewModel::class.java]


//        var  list = UserDatabase.getDatabase(requireContext()).depositDao().loadMixedDataAccept(0)
//        val array: Array<DepositDao.BalanceAmountCreditTypesHistory?> = list!!.toTypedArray()

        val position = resources.getStringArray(R.array.Departments)

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, position)
        binding.listPosition.setAdapter(arrayAdapter)

        binding.btnRegisterPersonal.setOnClickListener {
            val gUserId = insertDataToUserInDatabase()
            insertDataToEmployeeInDatabase(gUserId)
        }

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    //insert Data to Employee Entity
    private fun insertDataToEmployeeInDatabase(gUserid: Long) {

        val position = binding.listPosition.text.toString()
        val experience = binding.etExperience.text.toString()

        if (inputCheck(position, experience)) {

            val checkedTypeUser =
                binding.RadioGIsActivation.checkedRadioButtonId
            onRadioButtonClicked(checkedTypeUser)
            if (isActivation == 0) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                val formatted = current.format(formatter)

                // Create employee Object
                val employee = Employee(
                    0, position, experience, 0, formatted, gUserid
                )
                // Add Data to Database
                pViewModel.addPersonalInformation(employee)
            } else {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                val formatted = current.format(formatter)

                // Create employee Object
                val employee = Employee(
                    0, position, experience, 1, formatted, gUserid
                )
                // Add Data to Database
                pViewModel.addPersonalInformation(employee)
            }
            // Navigate Back
            findNavController().navigate(R.id.action_personalInformation_to_logInFragment)
            Toast.makeText(requireContext(), "Successfully login!", Toast.LENGTH_LONG).show()
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
    private fun insertDataToUserInDatabase(): Long {

        // Create user Object
        val user = User(
            0, nameData, emailData, passwordData, isClient
        )
        // Add Data to Database
        val gUserId = pViewModel.addUser(user)
        Toast.makeText(requireContext(), "Successfully login Employee!", Toast.LENGTH_LONG).show()


        return gUserId
    }


    private fun onRadioButtonClicked(checkedId: Int) {
        when (checkedId) {
            R.id.radioB_EmployeeActivation ->
                isActivation = 0
            R.id.radioB_AdminActivation ->
                isActivation = 1
        }
    }


}






