package com.example.roomapp.withDraw_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentWithdrawBinding
import com.example.roomapp.entitys.Withdraw
import com.example.roomapp.viewModel.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Withdraw : Fragment() {
    var client_ID: Int = 0
    private lateinit var preferences: SharedPreferences
    private lateinit var wViewModel: ViewModel

    private var _binding: FragmentWithdrawBinding? = null
    private val binding get() = _binding!!


    @RequiresApi(Build.VERSION_CODES.O)
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

        binding.tvBalanceAmountWithdraw.text = wViewModel.returnBalanceAmount(client_ID).toString()
        binding.tvCurrentBalanceAmountWithdraw.text =
            wViewModel.returnPendingBalance(client_ID).toString()

        binding.btnSubmitWithdraw.setOnClickListener {

            insertDataToWithdrawinDatabase()
        }

        return view
    }

    //insert Data to Withdraw Entity
    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToWithdrawinDatabase() {
        val inputWithdraw = binding.etWithdraw.text.toString()

        if (inputWithdraw.isNotEmpty()) {
            submit()

        } else {
            MaterialAlertDialogBuilder(requireContext()).setTitle("error")
                .setMessage("Invalid.Pleas input Withdraw").setPositiveButton("OK") {_,_ ->
            }.show()


        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun submit() {
        val moneySource = binding.etMoneySource.text.toString()
        val transactionID = UUID.randomUUID().toString()

        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = currentDate.format(formatter)

        val balanceAmountWithdraw = binding.tvBalanceAmountWithdraw.text.toString().toInt()
        val inputWithdraw = binding.etWithdraw.text.toString().toInt()
        val currentBalanceAmount = binding.tvCurrentBalanceAmountWithdraw.text.toString().toInt()

        val res = balanceAmountWithdraw.minus(currentBalanceAmount)
        if (inputWithdraw <= res) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Alert")
                .setMessage("Do you want to withdraw this number ")

                .setPositiveButton("Yes") { p0, p1 ->
                    // Create Withdraw Object
                    val withdraw = Withdraw(
                        0, transactionID, inputWithdraw.toString(), "Pending", moneySource,
                        formatted, "", "", client_ID, 0
                    )
                    // Add Data to Database
                    wViewModel.addWithdraw(withdraw)

                    Toast.makeText(
                        requireContext(),
                        "Successfully Saved!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    findNavController().navigate(R.id.action_withdraw_to_withdrawStatus)


                }
                .setNegativeButton("NO") { p0, p1 ->
                }
                .show()
            val returnPending = wViewModel.returnPendingBalance(client_ID)
            val resultPending = inputWithdraw + returnPending
            wViewModel.updatePendingBalance(resultPending, client_ID)

        } else {
            MaterialAlertDialogBuilder(requireContext()).setTitle("error")
                .setMessage("pleas number is bigger than amount Balance")
                .setPositiveButton("OK") { _, _ ->
                }.show()

        }


    }


}