package com.example.roomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.data.ViewModel
import com.example.roomapp.databinding.FragmentClientHomeBinding
import com.example.roomapp.databinding.FragmentLogInBinding
import com.example.roomapp.fragments.add.AdditionalInformationsArgs
import kotlinx.android.synthetic.main.fragment_client_home.*


class ClientHome : Fragment() {
    private lateinit var cViewModel: ViewModel
    var id1 :Int =0

    private var _binding: FragmentClientHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        cViewModel = ViewModelProvider(this).get(ViewModel::class.java)

//        Log.i("resurnUserAndClient", "onCreateView:${  cViewModel.returnUserAndClient().size} ")
//        cViewModel.returnUserAndClient()

        id1 = ClientHomeArgs.fromBundle(requireArguments()).id


        binding.btnWithdraw.setOnClickListener {
            findNavController().navigate(R.id.action_clientHome_to_withdraw)
        }

        binding.btnDeposit.setOnClickListener {
            val action =
                ClientHomeDirections.actionClientHomeToDeposit(id1)
            findNavController().navigate(action)

        }

return view

    }



}