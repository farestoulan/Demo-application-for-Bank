package com.example.roomapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.data.UserTuple
import com.example.roomapp.databinding.FragmentLogInBinding
import com.example.roomapp.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.view.*


class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.btnRegLogin.setOnClickListener {

            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }


        val email = view.findViewById<EditText>(R.id.et_EmailLogin)
        binding.btnSaveRegister.setOnClickListener {

            getData(email.text.toString())
        }
        return view

    }

    private fun getData(email: String) {

        val loginData = UserDatabase.getDatabase(requireContext()).userDao().logIn(email)
        if (loginData.isNotEmpty()) {

            findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
            Toast.makeText(
                requireContext(),
                "Login Sucssfully is: ${loginData.get(0).isClient} ",
                Toast.LENGTH_LONG
            ).show()


        } else {
            Toast.makeText(requireContext(), "Invalid:", Toast.LENGTH_LONG).show()
        }


    }


}