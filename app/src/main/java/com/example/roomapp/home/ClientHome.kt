package com.example.roomapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.viewModel.ViewModel
import com.example.roomapp.databinding.FragmentClientHomeBinding


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


//        id1 = ClientHomeArgs.fromBundle(requireArguments()).id


        binding.btnWithdraw.setOnClickListener {
            findNavController().navigate(R.id.action_clientHome_to_withdraw)
        }

        binding.btnDeposit.setOnClickListener {

            findNavController().navigate(R.id.action_clientHome_to_deposit)

        }

return view

    }



}