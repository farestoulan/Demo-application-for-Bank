package com.example.roomapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.Relationships.ClientWithDeposits
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.data.ViewModel
import com.example.roomapp.databinding.FragmentDepositAccessBinding


class DepositAccess : Fragment() {

    private lateinit var adapter: Adapter


    private var _binding: FragmentDepositAccessBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositAccessBinding.inflate(inflater, container, false)
        val view = binding.root


        adapter = Adapter(
            requireContext(), UserDatabase.getDatabase(requireContext())
                .clientDao().getClientWithDeposits()
        )
        binding.recycler.adapter = adapter






        return view
    }


}