package com.example.roomapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.adapteryDeposit.HistoryAdapter
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentEmployeeHistoryDepositBinding


class EmployeeHistoryDeposit : Fragment() {
    private lateinit var adapter: HistoryAdapter

    private var _binding: FragmentEmployeeHistoryDepositBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeHistoryDepositBinding.inflate(inflater, container, false)
        val view = binding.root
        var listHistory = UserDatabase.getDatabase(requireContext())
            .depositDao().loadMixedDataHistory()


        adapter = HistoryAdapter(requireContext(), listHistory)
        binding.recyclerHistory.adapter = adapter


        return view
    }


}