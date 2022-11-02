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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.roomapp.R
import com.example.roomapp.presentation.viewModels.ViewModel
import com.example.roomapp.databinding.FragmentClientHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ClientHome : Fragment() {
    var getID = 0
    private lateinit var dViewModel: ViewModel
    lateinit var preferences: SharedPreferences

    private var _binding: FragmentClientHomeBinding? = null
    private val binding get() = _binding!!
    //   private lateinit var  bottomNavigationView:BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val bottomNavigationView =
            binding.bottomNavigationViewClientHome
        val navController = findNavController()
        bottomNavigationView.setupWithNavController(navController)
        val mainDestinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->

                when (destination.id) {
                    R.id.clientHome -> bottomNavigationView.visibility = View.VISIBLE

                    R.id.depositStatus ->
                        bottomNavigationView.visibility = View.VISIBLE
                    R.id.withdrawStatus ->
                        bottomNavigationView.visibility = View.VISIBLE


                }
            }

        navController.addOnDestinationChangedListener(mainDestinationChangedListener)


        preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        dViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        getID = preferences.getInt("data", id)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Alert")
                .setMessage("Do you want to log out? ")
                .setPositiveButton("Yes") { _, _ ->

                    findNavController().navigate(R.id.action_clientHome_to_logInFragment)

                }.setNegativeButton("No") { _, _ ->

                }.show()
        }






        return view

    }


//    private fun getMyName() {
//        val name = dViewModel.getMyNameClient(requireContext(), getID.minus(1))
//        binding.titleClientHome.text = "Welcome  $name"
//    }

//    private fun getMyEmail() {
//        val email = dViewModel.getMyEmailClient(requireContext(), getID.minus(1))
//        binding.btnGetEmail.setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext()).setTitle("Your Email")
//                .setMessage("Your Email : $email").setPositiveButton("OK") { _, _ ->
//                }.show()
//        }
//    }

//    private fun getMyPassword() {
//        val password = dViewModel.getMyPasswordClient(requireContext(), getID.minus(1))
//        binding.btnGetPassword.setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext()).setTitle("Your Password")
//                .setMessage("Your Password : $password")
//                .setPositiveButton("OK") { _, _ ->
//                }.show()
//        }
//    }

//    private fun showBottomNav() {
//        bottomNavigationView.visibility = View.VISIBLE
//
//    }
//
//    private fun hideBottomNav() {
//        bottomNavigationView.visibility = View.GONE
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}