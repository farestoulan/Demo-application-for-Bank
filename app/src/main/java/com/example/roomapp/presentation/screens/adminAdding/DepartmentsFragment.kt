package com.example.roomapp.presentation.screens.adminAdding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentDepartmentsBinding
import com.example.roomapp.data.models.entitys.Departments
import com.example.roomapp.data.models.entitys.Roles
import com.example.roomapp.presentation.viewModels.ViewModel


class DepartmentsFragment : Fragment() {
    var branchID: Long = 0
    var departmentID: Long = 0
    private lateinit var dViewModel: ViewModel

    private var _binding: FragmentDepartmentsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDepartmentsBinding.inflate(inflater, container, false)
        val view = binding.root
        branchID = DepartmentsFragmentArgs.fromBundle(requireArguments()).branchID.toLong()

        dViewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_departmentsFragment_to_adminHomeFragment)
        }

        binding.btnSubmitDepartment.setOnClickListener {

            insertDataToDepartmentInDatabase()
            insertDataToRulesInDatabase()
            Toast.makeText(requireContext(), " Adding Successfully", Toast.LENGTH_LONG).show()

        }


        return view
    }

    //insert Data to Department Entity
    private fun insertDataToDepartmentInDatabase(): Long {
        val departmentName = binding.etAddDepartment.text.toString()
        // Create branch Object
        val departments = Departments(
            0, departmentName, branchID
        )
        // Add Data to Database
        departmentID = dViewModel.addDepartment(departments)

        binding.etAddDepartment.setText("")
        return departmentID

    }

    //insert Data to Rules Entity
    private fun insertDataToRulesInDatabase() {
        val rulesName = binding.etRules.text.toString()
        // Create branch Object
        val roles = Roles(
            0, rulesName, departmentID

        )
        // Add Data to Database
        dViewModel.addRules(roles)
        binding.etRules.setText("")

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}