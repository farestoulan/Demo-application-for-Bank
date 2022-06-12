package com.example.roomapp.deposit_Fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.adaptery.Adapter
import com.example.roomapp.dao.DepositDao
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentDepositAccessBinding
import kotlinx.android.synthetic.main.fragment_item_list_deposit_request.view.*


class DepositAccess : Fragment() {

    private lateinit var adapter: Adapter
    private lateinit var dViewModel: ViewModel


    private var _binding: FragmentDepositAccessBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositAccessBinding.inflate(inflater, container, false)
        val view = binding.root
        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        var list = UserDatabase.getDatabase(requireContext())
            .depositDao().loadMixedData()

        var mutableList = mutableListOf<DepositDao.BalanceAmountCreditTypes?>()

        if (list != null) {
            mutableList = list.toMutableList()
            adapter = Adapter(
                requireContext(), mutableList
            )
            binding.recycler.adapter = adapter
        }

        adapter.setClickListener(object : Adapter.ItemClickListener {
            override fun onItemClick(id: Int) {
                val clientID = mutableList?.get(id)?.clientID
                val valueDeposit = mutableList?.get(id)?.value_Deposit
                val balanceAmount = clientID?.let { dViewModel.returnBalanceAmount(it) }
                val valueAmountBalance = balanceAmount?.let { valueDeposit?.plus(it) }
                var depositID = mutableList?.get(0)?.deposit_Id
                depositID?.let { dViewModel.returnUpdate("Accept", it) }
                if (valueDeposit != null) {
                    valueAmountBalance?.let { dViewModel.returnBalance(it, clientID!!) }
                }
                adapter.removeAt(id)

            }

            override fun itemClick(id: Int) {
                var depositID = mutableList?.get(0)?.deposit_Id
                dViewModel.returnUpdate("Reject", depositID!!)
                adapter.removeAt(id)


            }

            override fun itemWattingClick(id: Int) {

                var depositID = mutableList?.get(0)?.deposit_Id
                dViewModel.returnUpdate("Waiting", depositID!!)

                binding.root.btnWatting.visibility = View.INVISIBLE


            }
        })

        return view
    }

}