package com.example.roomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.databinding.FragmentLogInBinding
import com.example.roomapp.databinding.FragmentWithdrawBinding


class Withdraw : Fragment() {
    private var _binding: FragmentWithdrawBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


}