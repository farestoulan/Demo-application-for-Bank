package com.example.roomapp.presentation.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentAdminHomeBinding
import com.example.roomapp.presentation.viewModels.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AdminHomeFragment : Fragment() {
    private var getID: Int = 0
    lateinit var aViewModel: ViewModel
    lateinit var preferences: SharedPreferences
    private var _binding: FragmentAdminHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        aViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        getID = preferences.getInt("data", id)

        binding.btnAddingBranch.setOnClickListener {
            findNavController().navigate(R.id.action_adminHomeFragment_to_branchesFragment2)
        }
        binding.btnAddRoles.setOnClickListener {
            findNavController().navigate(R.id.action_adminHomeFragment_to_rolesFragment)
        }
        binding.btnAddDepartment.setOnClickListener {
            findNavController().navigate(R.id.action_adminHomeFragment_to_departmentsFragment)
        }


        getMyName()
        getMyEmail()
        getMyPassword()

        return view
    }

    private fun getMyName() {
        val name = aViewModel.getMyNameAdmin(requireContext(), getID.minus(1))
        binding.titleHomeAdmin.text = "Welcome Admin $name"
    }

    private fun getMyEmail() {
        val email = aViewModel.getMyEmailAdmin(requireContext(), getID.minus(1))
        binding.btnGetEmailAdmin.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle(" Your Email")
                .setMessage("Your Email :$email")
                .setPositiveButton("OK") { _, _ ->

                }.show()
        }
    }

    private fun getMyPassword() {
        val password = aViewModel.getMyPasswordAdmin(requireContext(), getID.minus(1))
        binding.btnGetPasswordAdmin.setOnClickListener {
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