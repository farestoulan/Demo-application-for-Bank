package com.example.roomapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.adapteryDeposit.HistoryAdapter
import com.example.roomapp.adapteryWithdraw.HistoryAdapterWithdraw
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentEmployeeHistoryDepositBinding
import com.example.roomapp.databinding.FragmentEmployeeHistoryWithdrawBinding


class EmployeeHistoryWithdraw : Fragment() {
    private lateinit var adapter: HistoryAdapterWithdraw

    private var _binding: FragmentEmployeeHistoryWithdrawBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeHistoryWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root

        var listHistory =
            UserDatabase.getDatabase(requireContext()).withdrawDao().loadMixedDataHistoryWithdraw()


        adapter = HistoryAdapterWithdraw(requireContext(), listHistory)
        binding.recyclerHistoryWithdraw.adapter = adapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}