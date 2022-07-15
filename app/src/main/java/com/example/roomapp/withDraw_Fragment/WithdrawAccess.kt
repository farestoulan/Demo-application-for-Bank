package com.example.roomapp.withDraw_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.adapteryWithdraw.AdapterWithdraw
import com.example.roomapp.daoWithdraw.WithdrawDAO
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentWithdrawAccessBinding
import com.example.roomapp.viewModel.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class WithdrawAccess : Fragment() {
    var employeeID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var adapter: AdapterWithdraw
    private lateinit var wViewModel: ViewModel
    private var _binding: FragmentWithdrawAccessBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWithdrawAccessBinding.inflate(inflater, container, false)
        val view = binding.root
        wViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)

        val getID = preferences.getInt("data", id)
        employeeID = wViewModel.returnEmployeeID(getID)

        var listWithdraw = UserDatabase.getDatabase(requireContext())
            .withdrawDao().loadMixedData()
        var mutableListWithdraw = mutableListOf<WithdrawDAO.BalanceAmountCreditTypesWithdraw?>()

        if (listWithdraw != null) {
            mutableListWithdraw = listWithdraw.toMutableList()
            adapter = AdapterWithdraw(requireContext(), mutableListWithdraw)
            binding.recyclerWithdraw.adapter = adapter
        }
        adapter.setClickListener(object : AdapterWithdraw.ItemClickListenerWithdraw {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemClick(id: Int) {
                val name=  wViewModel.returnEmployeeName(employeeID)

                val currentDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatted = currentDate.format(formatter)

                val clientID = mutableListWithdraw?.get(id)?.clientID

                val valueWithdraw = mutableListWithdraw?.get(id)?.value_Withdraw
                val pending = wViewModel.returnPendingBalance(clientID!!)
                val resultPending = pending - valueWithdraw!!
                wViewModel.updatePendingBalance(resultPending,clientID)

                val balanceAmount = wViewModel.returnBalanceAmount(clientID!!)
                val valueAmountBalance = balanceAmount?.minus(valueWithdraw!!)

                val withdrawID = mutableListWithdraw.get(0)?.withdraw_Id

                withdrawID?.let { wViewModel.returnUpdateWithdraw("Accept", it) }
                if (withdrawID != null) {
                    wViewModel.updateApprovedDateWithdraw(formatted, withdrawID)
                }
                wViewModel.updateEmployeeName(name,withdrawID!!)


                if (valueWithdraw != null) {
                    valueAmountBalance?.let { wViewModel.returnBalance(it, clientID!!) }
                }
                adapter.removeAt(id)

            }

            override fun itemClick(id: Int) {
                val withdrawID = mutableListWithdraw.get(0)?.withdraw_Id
                val valueWithdraw = mutableListWithdraw?.get(id)?.value_Withdraw
                val clientID = mutableListWithdraw?.get(id)?.clientID

                val pending = wViewModel.returnPendingBalance(clientID!!)
                val resultPending = pending - valueWithdraw!!
                wViewModel.updatePendingBalance(resultPending,clientID)
                val name=  wViewModel.returnEmployeeName(employeeID)
                wViewModel.updateEmployeeName(name,withdrawID!!)

                withdrawID?.let { wViewModel.returnUpdateWithdraw("Reject", it) }
                adapter.removeAt(id)
            }

        })

        return view
    }


}