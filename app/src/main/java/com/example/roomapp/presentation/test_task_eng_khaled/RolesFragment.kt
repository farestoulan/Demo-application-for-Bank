package com.example.roomapp.presentation.test_task_eng_khaled

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomapp.databinding.FragmentRolesBinding


class RolesFragment : Fragment() {
   private var _binding:FragmentRolesBinding?=null
   private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentRolesBinding .inflate(inflater, container, false)

        val view = binding.root
        return view
    }


}