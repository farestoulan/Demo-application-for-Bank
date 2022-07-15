package com.example.roomapp.deposit_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.adapteryDeposit.Adapter
import com.example.roomapp.daoDeposit.DepositDao
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentDepositAccessBinding
import kotlinx.android.synthetic.main.fragment_item_list_deposit_request.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DepositAccess : Fragment() {
    var employeeID: Int = 0
    lateinit var preferences: SharedPreferences

    private lateinit var adapter: Adapter
    private lateinit var dViewModel: ViewModel


    private var _binding: FragmentDepositAccessBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositAccessBinding.inflate(inflater, container, false)
        val view = binding.root

        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)

        val getID = preferences.getInt("data", id)
        employeeID = dViewModel.returnEmployeeID(getID)

        var list = UserDatabase.getDatabase(requireContext())
            .depositDao().loadMixedData()

        var mutableList = mutableListOf<DepositDao.BalanceAmountCreditTypes?>()

        if (list != null) {
            mutableList = list.toMutableList()
            adapter = Adapter(
                requireContext(), mutableList
            )
            binding.recycler.adapter = adapter
        }

        adapter.setClickListener(object : Adapter.ItemClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemClick(id: Int) {

                val currentDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatted = currentDate.format(formatter)

                val clientID = mutableList?.get(id)?.clientID

                val valueDeposit = mutableList?.get(id)?.value_Deposit
                val balanceAmount = clientID?.let { dViewModel.returnBalanceAmount(it) }

                val valueAmountBalance = balanceAmount?.let { valueDeposit?.plus(it) }

                val depositID = mutableList?.get(0)?.deposit_Id

                depositID?.let { dViewModel.returnUpdate("Accept", it) }

                if (depositID != null) {
                    dViewModel.updateApprovedDate(formatted, depositID)
                }
                val name=  dViewModel.returnEmployeeName(employeeID)
                dViewModel.updateEmployeeName(name,depositID!!)

                if (valueDeposit != null) {
                    valueAmountBalance?.let { dViewModel.returnBalance(it, clientID!!) }
                }

                adapter.removeAt(id)

            }

            override fun itemClick(id: Int) {
                val depositID = mutableList?.get(0)?.deposit_Id
                dViewModel.returnUpdate("Reject", depositID!!)
                val name=  dViewModel.returnEmployeeName(employeeID)
                dViewModel.updateEmployeeName(name,depositID!!)
                adapter.removeAt(id)


            }

            override fun itemWattingClick(id: Int) {
                val depositID = mutableList?.get(0)?.deposit_Id
                dViewModel.returnUpdate("Pending", depositID!!)
                val name=  dViewModel.returnEmployeeName(employeeID)
                dViewModel.updateEmployeeName(name,depositID!!)

                binding.root.btnWatting.visibility = View.INVISIBLE


            }
        })

        return view
    }

}