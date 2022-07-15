package com.example.roomapp.screenViewpagerDeposit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.adapteryDeposit.HistoryAdapter
import com.example.roomapp.daoDeposit.DepositDao
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentRejectBinding
import com.example.roomapp.deposit_Fragment.DepositStatusDirections
import com.example.roomapp.viewModel.ViewModel


class RejectDepositFragment : Fragment() {
    private lateinit var pViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences

    private lateinit var adapter: HistoryAdapter

    private var _binding: FragmentRejectBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRejectBinding.inflate(inflater, container, false)
        val view = binding.root

        pViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        client_ID = pViewModel.returnClientID(getID)


        var listAccept =
            UserDatabase.getDatabase(requireContext()).depositDao().loadMixedDataReject(client_ID)


        adapter = HistoryAdapter(requireContext(), listAccept)
        binding.recyclerReject.adapter = adapter

        adapter.setClickListener(object : HistoryAdapter.ItemClickListener {


            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<DepositDao.BalanceAmountCreditTypesHistory?>?
            ) {
                client_ID = listAccept?.get(position)?.clientID!!

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


}