package com.example.roomapp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.databinding.FragmentLogInBinding
import com.example.roomapp.fragments.add.RegisterFragmentDirections


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root
        emailFocusListener()
        passwordFocusListener()

        binding.btnRegLogin.setOnClickListener {

            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }

        val email = view.findViewById<EditText>(R.id.et_EmailLogin)
        val password = view.findViewById<EditText>(R.id.et_Passwordlogin)

        binding.btnLogin.setOnClickListener {


            getData(email.text.toString(), password.text.toString())

        }
        return view

    }

    private fun getData(email: String, password: String) {

        val loginData = UserDatabase.getDatabase(requireContext()).userDao().logIn(email, password)

        if (loginData.isNotEmpty()) {
            val checkTypeUser = loginData.get(0).isClient
           val  id = loginData.get(0).id

            if (checkTypeUser == true) {
                val action =
                    LogInFragmentDirections.actionLogInFragmentToClientHome(id)
                findNavController().navigate(action)

            } else {

                findNavController().navigate(R.id.action_logInFragment_to_employeeHome)

            }

            Toast.makeText(
                requireContext(), "Login Sucssfully is: ${loginData.get(0).isClient} ",
                Toast.LENGTH_LONG
            ).show()

        }else{
            Toast.makeText(
                requireContext(), "invalid:pleas check fields  ",
                Toast.LENGTH_LONG
            ).show()

        }


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