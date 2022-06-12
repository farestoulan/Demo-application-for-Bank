package com.example.roomapp.withDraw_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.adaptery.Adapter
import com.example.roomapp.adaptery.AdapterWithdraw
import com.example.roomapp.dao.DepositDao
import com.example.roomapp.dao.WithdrawDAO
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.databinding.FragmentDepositAccessBinding
import com.example.roomapp.databinding.FragmentWithdrawAccessBinding
import com.example.roomapp.viewModel.ViewModel


class WithdrawAccess : Fragment() {
    private lateinit var adapter: AdapterWithdraw
    private lateinit var dViewModel: ViewModel
    private var _binding: FragmentWithdrawAccessBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWithdrawAccessBinding.inflate(inflater, container, false)
        val view = binding.root
        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)


        var listWithdraw = UserDatabase.getDatabase(requireContext())
            .withdrawDao().loadMixedData()
        var mutableListWithdraw = mutableListOf<WithdrawDAO.BalanceAmountCreditTypesWithdraw?>()

        if (listWithdraw != null) {
            mutableListWithdraw = listWithdraw.toMutableList()
            adapter = AdapterWithdraw(requireContext(), mutableListWithdraw)
            binding.recyclerWithdraw.adapter = adapter
        }
        adapter.setClickListener(object : AdapterWithdraw.ItemClickListenerWithdraw {
            override fun onItemClick(id: Int) {
                val clientID = mutableListWithdraw?.get(id)?.clientID
                val valueWithdraw = mutableListWithdraw?.get(id)?.value_Withdraw
                val balanceAmount = dViewModel.returnBalanceAmount(clientID!!)
                val valueAmountBalance = balanceAmount?.minus(valueWithdraw!!)
                val withdrawID = mutableListWithdraw.get(0)?.withdraw_Id
                withdrawID?.let { dViewModel.returnUpdateWithdraw("Accept", it) }
                if (valueWithdraw != null) {
                    valueAmountBalance?.let { dViewModel.returnBalance(it, clientID!!) }
                }
                adapter.removeAt(id)

            }

            override fun itemClick(id: Int) {
                dViewModel.returnUpdateWithdraw("Reject", id)
                adapter.removeAt(id)
            }

        })

        return view
    }


}