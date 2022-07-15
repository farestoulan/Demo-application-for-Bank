package com.example.roomapp.screenViewpagerDeposit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.adapteryDeposit.HistoryAdapter
import com.example.roomapp.daoDeposit.DepositDao
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentAcceptBinding
import com.example.roomapp.deposit_Fragment.DepositStatusDirections
import com.example.roomapp.viewModel.ViewModel

class AcceptDepositFragment : Fragment() {
    var resultFiltter: Int = 0
    private lateinit var aViewModel: ViewModel
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences

    private lateinit var adapter: HistoryAdapter
    private var _binding: FragmentAcceptBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAcceptBinding.inflate(inflater, container, false)
        val view = binding.root

        aViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val getID = preferences.getInt("data", id)
        client_ID = aViewModel.returnClientID(getID)

        val listAccept =
            UserDatabase.getDatabase(requireContext()).depositDao().loadMixedDataAccept(client_ID)

        adapter = HistoryAdapter(requireContext(), listAccept)
        binding.recyclerAccept.adapter = adapter

        aViewModel.messageOne.observe(viewLifecycleOwner, Observer {

            resultFiltter = it.toString().toInt()
        })


        aViewModel.messageTwo.observe(viewLifecycleOwner, Observer {

            val resultMessageTow = it.toString().toInt()
            val x = listAccept?.filter { it?.value_Deposit!! in resultFiltter..resultMessageTow }
            adapter = HistoryAdapter(requireContext(), x)
            binding.recyclerAccept.adapter = adapter
        })


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




