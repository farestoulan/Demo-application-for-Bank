package com.example.roomapp.presentation.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.models.UserSingleton
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.databinding.FragmentLogInBinding



class LogInFragment : Fragment() {
    lateinit var preferences: SharedPreferences


    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)

        emailFocusListener()
        passwordFocusListener()

        binding.btnRegLogin.setOnClickListener {

            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.et_EmailLogin)
            val password = view.findViewById<EditText>(R.id.et_Passwordlogin)
            getData(email.text.toString(), password.text.toString())


        }
        return view

    }

    private fun getData(email: String, password: String) {

        val loginData = UserDatabase.getDatabase(requireContext()).userDao().logIn(email, password)


        if (inputCheck(email, password)) {
            if (validEmail() == null) {
                if (validPassword() == null) {
                    if (loginData.size > 0) {
                        val checkTypeUser = loginData.get(0).isClient
                        val id = loginData.get(0).id

                        UserSingleton.setData(email, password, id)

                        val myId = preferences.edit()
                        myId.putInt("data", id)
                        myId.apply()

                        when (checkTypeUser) {
                            1 -> {
                                findNavController().navigate(R.id.action_logInFragment_to_clientHome)

                            }
                            0 -> {
                                findNavController().navigate(R.id.action_logInFragment_to_fragmentEmployeeHome)

                            }
                            2 -> {
                                findNavController().navigate(R.id.action_logInFragment_to_adminHomeFragment)

                            }
                            3 -> {
                                findNavController().navigate(R.id.action_logInFragment_to_managerHomeFragment2)

                            }
                        }

                        Toast.makeText(
                            requireContext(),
                            "Login Successfully",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            " invalid: This email does not exist please register ",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                } else {
                    Toast.makeText(
                        requireContext(), " invalid :Pleas check input field email Or password ",
                        Toast.LENGTH_LONG
                    ).show()


                }
            } else {
                Toast.makeText(
                    requireContext(), " invalid :Pleas check input field email Or password ",
                    Toast.LENGTH_LONG
                ).show()

            }
        } else {
            Toast.makeText(
                requireContext(), "invalid: Please input field ",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun inputCheck(
        email: String, password: String
    ): Boolean {
        return !(TextUtils.isEmpty(email) && password.isEmpty())

    }

    private fun emailFocusListener() {
        binding.etEmailLogin.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmailLogin.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.etPasswordlogin.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPasswordlogin.text.toString()
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