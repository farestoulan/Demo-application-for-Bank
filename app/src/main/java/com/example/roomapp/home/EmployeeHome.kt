package com.example.roomapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentEmployeeHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EmployeeHome : Fragment() {
    private var _binding: FragmentEmployeeHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().onBackPressedDispatcher.addCallback(this) {

            MaterialAlertDialogBuilder(requireContext()).setTitle("Alert")
                .setMessage("Do you want to log out? ")
                .setPositiveButton("Yes") { p0, p1 ->
                    findNavController().navigate(R.id.action_employeeHome_to_logInFragment)

                }.setNegativeButton("No") { p0, p1 ->

                }.show()
        }

        binding.btnDepositAccess.setOnClickListener {
            findNavController().navigate(R.id.action_employeeHome_to_depositAccess)
        }
        binding.btnWithdrawAccess.setOnClickListener {
            findNavController().navigate(R.id.action_employeeHome_to_withdrawAccess)
        }
        binding.btnHistoryDeposit.setOnClickListener {
            findNavController().navigate(R.id.action_employeeHome_to_employeeHistory)
        }
        binding.btnHistoryWithdraw.setOnClickListener {
            findNavController().navigate(R.id.action_employeeHome_to_employeeHistoryWithdraw)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}