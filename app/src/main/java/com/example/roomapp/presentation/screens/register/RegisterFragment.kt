package com.example.roomapp.presentation.screens.register

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    var isClient: Int = 0
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        emailFocusListener()
        passwordFocusListener()
        binding.btnSubmitRegester.setOnClickListener {
            val name = binding.etNameRegister.text.toString().trim()
            val email = binding.etEmailRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()

            val register =
                UserDatabase.getDatabase(requireContext()).userDao().logIn(email, password)
            if (inputCheck(name, email, password)) {

                if (validEmail() == null) {

                    if (validPassword() == null) {

                        if (register.size == 0) {


                            val checkedTypeUser =
                                binding.RadioGDataTaype.checkedRadioButtonId
                            onRadioButtonClicked(checkedTypeUser)
                            when (isClient) {
                                1 -> {
                                    val action =
                                        RegisterFragmentDirections.actionRegisterFragmentToAdditionalInformations(
                                            name,
                                            email,
                                            password
                                        )

                                    findNavController().navigate(action)


                                }
                                0 -> {
                                    val action =
                                        RegisterFragmentDirections.actionRegisterFragmentToPersonalInformation(
                                            name,
                                            email,
                                            password
                                        )
                                    findNavController().navigate(action)


                                }
                                2 -> {
                                    val action =
                                        RegisterFragmentDirections.actionRegisterFragmentToAdminFragment(
                                            name, email, password

                                        )
                                    findNavController().navigate(action)
                                }
                                else -> {
                                    val action =
                                        RegisterFragmentDirections.actionRegisterFragmentToManagerDirectorFragment2(
                                            name, email, password
                                        )

                                    findNavController().navigate(action)
                                }
                            }

                        } else if (register.size == 1) {
                            Toast.makeText(
                                requireContext(),
                                "Invalid: This email already exists",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Invalid Pleas check fields.",
                            Toast.LENGTH_LONG
                        ).show()

                    }


                } else {
                    Toast.makeText(
                        requireContext(),
                        "Invalid Pleas check fields",
                        Toast.LENGTH_LONG
                    ).show()

                }
            } else {
                Toast.makeText(requireContext(), "Invalid : pleas input field", Toast.LENGTH_LONG)
                    .show()
            }

        }
        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)

        }


        return view
    }

    private fun inputCheck(
        name: String, email: String, password: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && password.isEmpty())

    }


    fun onRadioButtonClicked(checkedId: Int) {
        when (checkedId) {
            R.id.radioB_Employee ->

                isClient = 0


            R.id.radioB_Client ->

                isClient = 1

            R.id.radioB_Admin ->
                isClient = 2

            R.id.radioB_Boss ->
                isClient = 3
        }
    }


    private fun emailFocusListener() {
        binding.etEmailRegister.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmailRegister.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.etPasswordRegister.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPasswordRegister.text.toString()
        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }
}




