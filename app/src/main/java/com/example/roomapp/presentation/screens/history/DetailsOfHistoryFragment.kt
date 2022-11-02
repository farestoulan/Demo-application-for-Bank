package com.example.roomapp.presentation.screens.history

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.databinding.FragmentDetalisOfHistoryBinding
import com.example.roomapp.presentation.viewModels.ViewModel


class DetailsOfHistoryFragment : Fragment() {
    private lateinit var aViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private var _binding: FragmentDetalisOfHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalisOfHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        aViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        client_ID = aViewModel.returnClientID(getID)
        val sourceOfCash = aViewModel.returnMoneySource(client_ID)

        binding.DetailsSourceOfCode.text = sourceOfCash


        val clientName = DetailsOfHistoryFragmentArgs.fromBundle(requireArguments()).clientName
        val transactionID =
            DetailsOfHistoryFragmentArgs.fromBundle(requireArguments()).transactionID
        val amount = DetailsOfHistoryFragmentArgs.fromBundle(requireArguments()).amountBalance
        val employeeName = DetailsOfHistoryFragmentArgs.fromBundle(requireArguments()).employeeName
        binding.clientNameDetails.text = clientName
        binding.tvDetailsTransaction.text = transactionID
        binding.amountDetalis.text = amount.toString()
        binding.tvEmployeeNameDetailes.text = employeeName

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}