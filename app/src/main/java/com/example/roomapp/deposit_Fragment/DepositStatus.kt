package com.example.roomapp.deposit_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.roomapp.R
import com.example.roomapp.adapteryDeposit.AdapterViewpager
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.databinding.FragmentDepositStatusBinding
import com.example.roomapp.viewModel.ViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class DepositStatus : Fragment() {
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var dViewModel: ViewModel

   private lateinit var tabLayout: TabLayout
   private lateinit var viewPager: ViewPager2

    private var _binding: FragmentDepositStatusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositStatusBinding.inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        dViewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

        val getID = preferences.getInt("data", id)
        client_ID = dViewModel.returnClientID(getID)

       binding.tv2BalanceNumber.text =  dViewModel.returnBalanceAmount(client_ID).toString()


        tabLayout = binding.tabLayout
        viewPager = binding.viewpager
        viewPager.adapter = AdapterViewpager(this)
        tabLayout.isSelected


binding.btnAplay.setOnClickListener {
    val messageOne = binding.etFrom.text.toString()
    val messageTwo = binding.etTo.text.toString()
dViewModel.getValues(messageOne,messageTwo)
}




        TabLayoutMediator(tabLayout , viewPager){tab ,index ->

            tab.text = when(index){
                0 -> {resources.getString(R.string.title_accept)}
                1 -> {resources.getString(R.string.title_pending)}
                2 -> {resources.getString(R.string.title_reject)}
                else -> {throw  Resources.NotFoundException("Position Not Found")}
            }

        }.attach()

        binding.floatingAction.setOnClickListener {
            findNavController().navigate(R.id.action_depositStatus_to_deposit)
        }


        return view
    }


}