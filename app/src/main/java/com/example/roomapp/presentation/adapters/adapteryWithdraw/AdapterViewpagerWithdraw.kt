package com.example.roomapp.presentation.adapters.adapteryWithdraw

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomapp.presentation.screens.screenViewpagerWithdraw.AcceptWithdrawFragment
import com.example.roomapp.presentation.screens.screenViewpagerWithdraw.PendingWithdrawFragment
import com.example.roomapp.presentation.screens.screenViewpagerWithdraw.RejectWithdrawFragment

class AdapterViewpagerWithdraw(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AcceptWithdrawFragment()
            }
            1 -> {
                PendingWithdrawFragment()
            }
            2 -> {
                RejectWithdrawFragment()
            }

            else -> {
                throw  Resources.NotFoundException("Position Not Found")
            }
        }
    }
}