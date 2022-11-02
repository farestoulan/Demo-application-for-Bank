package com.example.roomapp.presentation.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.databinding.FragmentManagerHomeBinding
import com.example.roomapp.presentation.viewModels.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ManagerHomeFragment : Fragment() {
    private var getID: Int = 0
    lateinit var aViewModel: ViewModel
    lateinit var preferences: SharedPreferences
    private var _binding : FragmentManagerHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManagerHomeBinding .inflate(inflater, container, false)
        val view = binding.root
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        aViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        getID = preferences.getInt("data", id)

        getMyName()
        getMyEmail()
        getMyPassword()

        return view


    }


    private fun getMyName() {
        val name = aViewModel.getMyNameBoss(requireContext(), getID.minus(1))
        binding.titleHomeBoss.text = "Welcome Boss $name"
    }

    private fun getMyEmail() {
        val email = aViewModel.getMyEmailBoss(requireContext(), getID.minus(1))
        binding.btnGetEmailBoss.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle(" Your Email")
                .setMessage("Your Email :$email")
                .setPositiveButton("OK") { _, _ ->

                }.show()
        }
    }

    private fun getMyPassword() {
        val password = aViewModel.getMyPasswordBoss(requireContext(), getID.minus(1))
        binding.btnGetPasswordBoss.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Your Password")
                .setMessage("Your Password :$password")
                .setPositiveButton("OK") { _, _ ->

                }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}