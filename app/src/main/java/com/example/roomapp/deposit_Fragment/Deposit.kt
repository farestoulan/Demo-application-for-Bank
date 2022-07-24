package com.example.roomapp.deposit_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.entitys.DepositEntity
import com.example.roomapp.R
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentDepositBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Deposit : Fragment() {
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var dViewModel: ViewModel
    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)

        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        val getID = preferences.getInt("data", id)
        client_ID = dViewModel.returnClientID(getID)



        dViewModel.returnDate(client_ID)

        binding.btnSubmitDeposit.setOnClickListener {
            insertDataToDepositinDatabase()
        }

        return view
    }


    //insert Data to Deposit Entity
    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToDepositinDatabase() {
        val moneySource = binding.etMoneySourceDeposit.text.toString()

        val transactionID = UUID.randomUUID().toString()

        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = currentDate.format(formatter)

        val inputDeposit = binding.etDeposit.text.toString()
        if (inputCheck(inputDeposit)) {
            // Create deposit Object
            val deposit = DepositEntity(
                0, transactionID, inputDeposit, "Pending", moneySource,
                formatted, "", "", client_ID, 0
            )
            // Add Data to Database
            dViewModel.addDeposit(deposit)
            findNavController().navigate(R.id.action_deposit_to_clientHome)
            Toast.makeText(requireContext(), "Successfully Saved!", Toast.LENGTH_LONG).show()


        } else {

            MaterialAlertDialogBuilder(requireContext()).setTitle("error").setMessage("Invalid.Pleas input Deposit")
                .setPositiveButton("OK") { _, _ ->
                }.show()
        }

    }
}

private fun inputCheck(
    inputDeposit: String,

    ): Boolean {
    return !(TextUtils.isEmpty(inputDeposit))
}


