package com.example.roomapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.data.DepositEntity
import com.example.roomapp.data.ViewModel
import com.example.roomapp.databinding.FragmentDepositBinding


class Deposit : Fragment() {
    var id1 :Int =0

    private lateinit var dViewModel: ViewModel

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        val view = binding.root
        id1 = DepositArgs.fromBundle(requireArguments()).id

        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.btnSubmitDeposit.setOnClickListener {
            insertDataToDepositinDatabase()
        }

        return view
    }


    //insert Data to Employee Entity
    private fun insertDataToDepositinDatabase() {
        val inputDeposit = binding.etDeposit.text.toString()

        if (inputCheck(inputDeposit)) {
            // Create employee Object
            val deposit = DepositEntity(0,0,inputDeposit,"Waiting",id1)
            // Add Data to Database
            dViewModel.addDeposit(deposit)
            Toast.makeText(requireContext(), "Successfully Saved!", Toast.LENGTH_LONG).show()


        } else {
            Toast.makeText(requireContext(), "Invalid.Pleas input Deposit", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        inputDeposit: String,

    ): Boolean {
        return !(TextUtils.isEmpty(inputDeposit) )
    }




}