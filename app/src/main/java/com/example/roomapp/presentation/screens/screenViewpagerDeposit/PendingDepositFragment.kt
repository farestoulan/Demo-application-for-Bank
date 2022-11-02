package com.example.roomapp.presentation.screens.screenViewpagerDeposit

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
import com.example.roomapp.databinding.FragmentPendingBinding
import com.example.roomapp.presentation.screens.deposit_Fragment.DepositStatusDirections
import com.example.roomapp.presentation.viewModels.ViewModel


class PendingDepositFragment : Fragment() {
    private lateinit var pViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences

    private lateinit var adapter: HistoryAdapter
    private var _binding: FragmentPendingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPendingBinding.inflate(inflater, container, false)
        val view = binding.root

        pViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        client_ID = pViewModel.returnClientID(getID)

        val listPending =
            UserDatabase.getDatabase(requireContext()).depositDao().loadMixedDataPending(client_ID)

        adapter = HistoryAdapter(requireContext(), listPending)
        binding.recyclerPending.adapter = adapter

        adapter.setClickListener(object : HistoryAdapter.ItemClickListener {
            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<DepositDao.BalanceAmountCreditTypesHistory?>?
            ) {
                client_ID = listPending?.get(position)?.clientID!!

                val action =
                    DepositStatusDirections.actionDepositStatusToDetailsOfHistoryFragment(
                        mData?.get(
                            position
                        )?.transactionID,
                        mData?.get(position)?.userName,
                        mData?.get(position)?.value_Deposit!!.toInt(),
                        mData?.get(position)?.employeeName

                    )
                findNavController().navigate(action)
            }
        })

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}