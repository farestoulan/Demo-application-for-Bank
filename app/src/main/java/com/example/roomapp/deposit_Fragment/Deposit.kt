package com.example.roomapp.deposit_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.entitys.DepositEntity
import com.example.roomapp.R
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentDepositBinding


class Deposit : Fragment() {
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var dViewModel: ViewModel

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        val view = binding.root
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)

        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)



        var getID = preferences.getInt("data", id)
        client_ID = dViewModel.returnClientID(getID)
        binding.tvBalanceAmount.text = "Balance Amount" + dViewModel.returnBalanceAmount(client_ID)
    //    binding.tvStuts.text = dViewModel.returnStatus(client_ID)

        binding.btnSubmitDeposit.setOnClickListener {
            insertDataToDepositinDatabase()
        }

        return view
    }


    //insert Data to Deposit Entity
    private fun insertDataToDepositinDatabase() {
        val inputDeposit = binding.etDeposit.text.toString()
        if (inputCheck(inputDeposit)) {
            // Create deposit Object
            val deposit = DepositEntity(
                0, inputDeposit, "New deposit",
                client_ID
            )
            // Add Data to Database
            dViewModel.addDeposit(deposit)
            findNavController().navigate(R.id.action_deposit_to_clientHome)
            Toast.makeText(requireContext(), "Successfully Saved!", Toast.LENGTH_LONG).show()


        } else {
            Toast.makeText(requireContext(), "Invalid.Pleas input Deposit", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(
        inputDeposit: String,

        ): Boolean {
        return !(TextUtils.isEmpty(inputDeposit))
    }


}