package com.example.roomapp.adapteryDeposit

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomapp.screenViewpagerDeposit.AcceptDepositFragment
import com.example.roomapp.screenViewpagerDeposit.PendingDepositFragment
import com.example.roomapp.screenViewpagerDeposit.RejectDepositFragment

class AdapterViewpager(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AcceptDepositFragment()
            }
            1 -> {
                PendingDepositFragment()
            }
            2 -> {
                RejectDepositFragment()
            }

            else -> {
                throw  Resources.NotFoundException("Position Not Found")
            }
        }
    }
}