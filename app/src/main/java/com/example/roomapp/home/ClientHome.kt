package com.example.roomapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentClientHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ClientHome : Fragment() {
    private var _binding: FragmentClientHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientHomeBinding.inflate(inflater, container, false)
        val view = binding.root


       requireActivity().onBackPressedDispatcher.addCallback(this) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Alert")
                .setMessage("Do you want to log out? ")
                .setPositiveButton("Yes") { _, _ ->

                    findNavController().navigate(R.id.action_clientHome_to_logInFragment)

                }.setNegativeButton("No") { _, _ ->

                }.show()
        }




        return view

    }


}