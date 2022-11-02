package com.example.roomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.roomapp.databinding.FragmentEmployeeHomeBinding
import com.example.roomapp.databinding.FragmentTransationsEmployeeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FragmentEmployeeHome : Fragment() {
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
                    findNavController().navigate(R.id.action_transactionEmployee_to_fragmentEmployeeHome)

                }.setNegativeButton("No") { p0, p1 ->

                }.show()
        }

        val bottomNavigationView =
            binding.bottomNavigationViewEmployeeHome
        val navController = findNavController()
        bottomNavigationView.setupWithNavController(navController)
        val mainDestinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->

                when (destination.id) {
                    R.id.fragmentEmployeeHome ->
                        bottomNavigationView.visibility = View.VISIBLE

                    R.id.transactionEmployee ->
                        bottomNavigationView.visibility = View.VISIBLE

                }
            }

        navController.addOnDestinationChangedListener(mainDestinationChangedListener)

        return  view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}