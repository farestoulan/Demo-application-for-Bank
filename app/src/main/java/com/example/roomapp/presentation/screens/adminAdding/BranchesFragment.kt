package com.example.roomapp.presentation.screens.adminAdding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.databinding.FragmentBranchesBinding
import com.example.roomapp.data.models.entitys.Branches
import com.example.roomapp.presentation.viewModels.ViewModel


class BranchesFragment : Fragment() {
    var branchID :Long = 0
    private lateinit var bViewModel: ViewModel
    private var _binding: FragmentBranchesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBranchesBinding.inflate(inflater, container, false)
      val view=  binding.root
        bViewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding.btnAddBranch.setOnClickListener {
            insertDataToBranchInDatabase()
        }


        return view
    }

    //insert Data to Branch Entity
     fun insertDataToBranchInDatabase() :Long{
        val branchName = binding.etAddBranch.text.toString()

        // Create branch Object
        val branches = Branches(
            0, branchName

        )
        // Add Data to Database
      branchID=   bViewModel.addBranch(branches)
        val action=BranchesFragmentDirections.actionBranchesFragment2ToDepartmentsFragment(branchID.toInt())

        findNavController().navigate(action)
        Toast.makeText(requireContext(), "Branch Adding Successfully", Toast.LENGTH_LONG).show()
        return branchID


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}