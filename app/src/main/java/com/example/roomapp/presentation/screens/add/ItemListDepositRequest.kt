package com.example.roomapp.presentation.screens.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.databinding.FragmentItemListDepositRequestBinding


class ItemListDepositRequest : Fragment() {


    private var _binding: FragmentItemListDepositRequestBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListDepositRequestBinding.inflate(inflater, container, false)
        val view = binding.root




        return view
    }


}