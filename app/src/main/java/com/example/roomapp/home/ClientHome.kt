package com.example.roomapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentClientHomeBinding


class ClientHome : Fragment() {
    private lateinit var cViewModel: ViewModel
    private var _binding: FragmentClientHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        cViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.btnWithdraw.setOnClickListener {
            findNavController().navigate(R.id.action_clientHome_to_withdrawStatus)
        }

        binding.btnDeposit.setOnClickListener {

            findNavController().navigate(R.id.action_clientHome_to_depositStatus)

        }

return view

    }



}