package com.example.roomapp.presentation.screens.history

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.presentation.adapters.adapteryDeposit.HistoryAdapter
import com.example.roomapp.data.models.dAO.DepositDao
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.databinding.FragmentEmployeeHistoryDepositBinding
import com.example.roomapp.presentation.viewModels.ViewModel


class EmployeeHistoryDeposit : Fragment() {
    private lateinit var pViewModel: ViewModel
    var employee_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var adapter: HistoryAdapter

    private var _binding: FragmentEmployeeHistoryDepositBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeHistoryDepositBinding.inflate(inflater, container, false)
        val view = binding.root
        pViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        employee_ID = pViewModel.returnEmployeeID(getID)

        val listHistory = UserDatabase.getDatabase(requireContext())
            .depositDao().loadMixedDataHistory(employee_ID)


        adapter = HistoryAdapter(requireContext(), listHistory)
        binding.recyclerHistory.adapter = adapter

        adapter.setClickListener(object :HistoryAdapter.ItemClickListener{
            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<DepositDao.BalanceAmountCreditTypesHistory?>?
            ) {

                val action =
                    EmployeeHistoryDepositDirections.actionEmployeeHistoryToDetailsOfHistoryFragment(
                        mData?.get(
                            position
                        )?.transactionID,
                        mData?.get(position)?.userName,
                        mData?.get(position)?.value_Deposit!!.toInt(),
                        mData[position]?.employeeName
                    )
                findNavController().navigate(action)
            }

        })


        return view
    }


}