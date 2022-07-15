package com.example.roomapp.screenViewpagerWithdraw

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.adapteryWithdraw.HistoryAdapterWithdraw
import com.example.roomapp.daoWithdraw.WithdrawDAO
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentRejectWithdrawBinding
import com.example.roomapp.deposit_Fragment.DepositStatusDirections
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.withDraw_Fragment.WithdrawStatusDirections


class RejectWithdrawFragment : Fragment() {
    private lateinit var rViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences

    private lateinit var adapter: HistoryAdapterWithdraw
    private var _binding: FragmentRejectWithdrawBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRejectWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root

        rViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        var getID = preferences.getInt("data", id)
        client_ID = rViewModel.returnClientID(getID)

        var listReject =
            UserDatabase.getDatabase(requireContext()).withdrawDao()
                .loadMixedDataRejectWithdraw(client_ID)
        adapter = HistoryAdapterWithdraw(
            requireContext(), listReject
        )
        binding.recyclerRejectWithdraw.adapter = adapter

        adapter.setClickListener(object : HistoryAdapterWithdraw.ItemClickListenerWithdraw {
            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?
            ) {
                client_ID = listReject?.get(position)?.clientID!!

                val action =
                    WithdrawStatusDirections.actionWithdrawStatusToDetailsOfHistoryFragment(
                        mData?.get(
                            position
                        )?.transactionID,
                        mData?.get(position)?.userName,
                        mData?.get(position)?.value_Withdraw!!.toInt(),
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