package com.example.roomapp.presentation.screens.screenViewpagerWithdraw

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.presentation.adapters.adapteryWithdraw.HistoryAdapterWithdraw
import com.example.roomapp.data.models.dAO.WithdrawDAO
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.databinding.FragmentPendigWithdrawBinding
import com.example.roomapp.presentation.viewModels.ViewModel
import com.example.roomapp.presentation.screens.withDraw_Fragment.WithdrawStatusDirections


class PendingWithdrawFragment : Fragment() {
    private lateinit var pViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var adapter: HistoryAdapterWithdraw

    private var _binding: FragmentPendigWithdrawBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendigWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root

        pViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        client_ID = pViewModel.returnClientID(getID)


        val listPending =
            UserDatabase.getDatabase(requireContext()).withdrawDao()
                .loadMixedDataPendingWithdraw(client_ID)
        adapter = HistoryAdapterWithdraw(
            requireContext(), listPending
        )
        binding.recyclerPendingWithdraw.adapter = adapter

        adapter.setClickListener(object :HistoryAdapterWithdraw .ItemClickListenerWithdraw{
            override fun onItemClick(
                view: View,
                position: Int,
                mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?
            ) {
                client_ID = listPending?.get(position)?.clientID!!

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