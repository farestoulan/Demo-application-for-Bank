package com.example.roomapp.presentation.screens.history

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomapp.presentation.adapters.adapteryWithdraw.HistoryAdapterWithdraw
import com.example.roomapp.data.models.dAO.WithdrawDAO
import com.example.roomapp.data.data_source.local.database.UserDatabase
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

        val listHistory =
            UserDatabase.getDatabase(requireContext()).withdrawDao().loadMixedDataHistoryWithdraw()


        adapter = HistoryAdapterWithdraw(requireContext(), listHistory)
        binding.recyclerHistoryWithdraw.adapter = adapter

        adapter.setClickListener(object :HistoryAdapterWithdraw.ItemClickListenerWithdraw{
            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?
            ) {
                val action =
                    EmployeeHistoryWithdrawDirections.actionEmployeeHistoryWithdrawToDetailsOfHistoryFragment(
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