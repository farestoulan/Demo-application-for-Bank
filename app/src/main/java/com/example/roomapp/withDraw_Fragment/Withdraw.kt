package com.example.roomapp.withDraw_Fragment

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
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentWithdrawBinding
import com.example.roomapp.entitys.Withdraw
import com.example.roomapp.viewModel.ViewModel


class Withdraw : Fragment() {
    var client_ID: Int = 0
    private lateinit var preferences: SharedPreferences
    private lateinit var wViewModel: ViewModel

    private var _binding: FragmentWithdrawBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        wViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        var getID = preferences.getInt("data", id)
        client_ID = wViewModel.returnClientID(getID)

        binding.tvBalanceAmount.text = wViewModel.returnBalanceAmount(client_ID).toString()

        binding.btnSubmitWithdraw.setOnClickListener {
            insertDataToWithdrawinDatabase()

        }

        return view
    }


    //insert Data to Withdraw Entity
    private fun insertDataToWithdrawinDatabase() {
        val inputWithdraw = binding.etWithdraw.text.toString()
        val balanceAmount = binding.tvBalanceAmount.text.toString()
        if (inputWithdraw < balanceAmount) {

            if (inputCheck(inputWithdraw)) {
                // Create Withdraw Object
                val withdraw = Withdraw(
                    0, inputWithdraw, "Waiting for the last Withdraw",
                    client_ID
                )
                // Add Data to Database
                wViewModel.addWithdraw(withdraw)
                findNavController().navigate(R.id.action_withdraw_to_clientHome)
                Toast.makeText(requireContext(), "Successfully Saved!", Toast.LENGTH_LONG).show()


            } else {
                Toast.makeText(requireContext(), "Invalid.Pleas input Withdraw", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Invalid: The value is greater than the current balance ",
                Toast.LENGTH_LONG
            )
                .show()

        }
    }

    private fun inputCheck(
        inputDeposit: String,

        ): Boolean {
        return !(TextUtils.isEmpty(inputDeposit))
    }


}