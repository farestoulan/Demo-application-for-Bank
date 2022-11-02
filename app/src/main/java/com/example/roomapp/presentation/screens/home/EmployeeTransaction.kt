package com.example.roomapp.presentation.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.models.UserSingleton
import com.example.roomapp.databinding.FragmentEmployeeHomeBinding
import com.example.roomapp.databinding.FragmentTransationsEmployeeBinding
import com.example.roomapp.presentation.viewModels.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EmployeeTransaction : Fragment() {
    var getID: Int = 0
    private lateinit var dViewModel: ViewModel
    lateinit var preferences: SharedPreferences
    private var _binding: FragmentTransationsEmployeeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransationsEmployeeBinding.inflate(inflater, container, false)
        val view = binding.root
        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        dViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        getID = preferences.getInt("data", id)



        requireActivity().onBackPressedDispatcher.addCallback(this) {


                    findNavController().navigate(R.id.action_transactionEmployee_to_fragmentEmployeeHome)


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

        getMyName()
        getMyEmail()
        getMyPassword()

        return view
    }

    private fun getMyName() {
        val name = dViewModel.getMyName(requireContext(), getID.minus(1))
        binding.titleHomeEmployee.text = "Welcome $name"
    }

    private fun getMyEmail() {
        val email = dViewModel.getMyEmail(requireContext(), getID.minus(1))
        binding.btnGetEmailEmployee.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Your Email")
                .setMessage("Your Email : $email").setPositiveButton("ok") { _, _ ->

                }.show()
        }
    }

    private fun getMyPassword() {
        val email = dViewModel.getMyPassword(requireContext(), getID.minus(1))
        binding.btnGetPasswordEmployee.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Your Password")
                .setMessage("Your Password :$email")
                .setPositiveButton("ok") { _, _ ->

                }.show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}