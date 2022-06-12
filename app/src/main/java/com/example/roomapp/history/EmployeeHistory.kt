package com.example.roomapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.adaptery.AdapterWithdraw
import com.example.roomapp.adaptery.HistoryAdapter
import com.example.roomapp.dao.DepositDao
import com.example.roomapp.dao.EmployeeDao
import com.example.roomapp.dao.WithdrawDAO
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.databinding.FragmentEmployeeHistoryBinding
import com.example.roomapp.databinding.FragmentWithdrawAccessBinding


class EmployeeHistory : Fragment() {
    private lateinit var adapter: HistoryAdapter

    private var _binding: FragmentEmployeeHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        var listHistory = UserDatabase.getDatabase(requireContext())
            .depositDao().loadMixedDataHistory()


            adapter = HistoryAdapter(requireContext(), listHistory)
            binding.recyclerHistory.adapter = adapter


        return view
    }


}