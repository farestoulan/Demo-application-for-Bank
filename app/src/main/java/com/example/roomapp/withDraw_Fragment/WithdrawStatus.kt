package com.example.roomapp.withDraw_Fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.roomapp.R
import com.example.roomapp.adapteryWithdraw.AdapterViewpagerWithdraw
import com.example.roomapp.databinding.FragmentWithdrawStatusBinding
import com.example.roomapp.viewModel.ViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class WithdrawStatus : Fragment() {
    var client_ID: Int = 0
    lateinit var preferences: SharedPreferences
    private lateinit var dViewModel: ViewModel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private var _binding :FragmentWithdrawStatusBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =FragmentWithdrawStatusBinding. inflate(inflater, container, false)
        val view = binding.root

        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        dViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        var getID = preferences.getInt("data", id)
        client_ID = dViewModel.returnClientID(getID)
        binding.tvBalanceNumber.text = dViewModel.returnBalanceAmount(client_ID).toString()


        tabLayout = binding.tabLayoutWithdraw
        viewPager = binding.viewpagerWithdraw
        viewPager.adapter = AdapterViewpagerWithdraw(this)
        tabLayout.isSelected

        TabLayoutMediator(tabLayout , viewPager){tab ,index ->

            tab.text = when(index){
                0 -> {resources.getString(R.string.title_accept)}
                1 -> {resources.getString(R.string.title_pending)}
                2 -> {resources.getString(R.string.title_reject)}
                else -> {throw  Resources.NotFoundException("Position Not Found")}
            }


        }.attach()


        binding.floatingActionWithdraw.setOnClickListener {
            findNavController().navigate(R.id.action_withdrawStatus_to_withdraw)
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }


}