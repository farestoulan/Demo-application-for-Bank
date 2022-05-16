package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {
    var isClient: Boolean = false
    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        }

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        binding.btnSaveRegister1.setOnClickListener {
            val checkedTypeUser = binding.RadioGDataTaype.checkedRadioButtonId
            onRadioButtonClicked(checkedTypeUser)

            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = et_NameRegister.text.toString()
        val email = et_EmailRegister.text.toString()
        val password = et_PasswordRegister.text

        if (inputCheck(name, email, password!!)) {
            // Create User Object
            val user = User(0, name, email, Integer.parseInt(password.toString()), isClient)
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully login!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        } else {
            Toast.makeText(requireContext(), "Invalid.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, email: String, password: Editable): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && password.isEmpty())
    }


    fun onRadioButtonClicked(checkedId: Int) {


        when (checkedId) {
            R.id.radioB_Employee ->

                isClient = false

            R.id.radioB_Client ->

                isClient = true
        }
    }
}




