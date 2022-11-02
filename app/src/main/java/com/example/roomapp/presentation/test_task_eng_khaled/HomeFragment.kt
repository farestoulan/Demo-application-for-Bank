package com.example.roomapp.presentation.test_task_eng_khaled

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterHorizontalRecycler
    private lateinit var cAdapter: AdapterBottomRecyclerHome
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val image = listOf<Image>(
            Image(R.drawable.maskgroup3),
            Image(R.drawable.maskgroup),
            Image(R.drawable.maskgroup2),
        )

        recyclerView = binding.bottomRecycler
        cAdapter = AdapterBottomRecyclerHome(image)
        recyclerView.adapter = cAdapter
//        val displaymetrics = DisplayMetrics()
//
//        val a = displaymetrics.heightPixels * 60 / 60
//        recyclerView.layoutParams.height = a



        val images = listOf<Image>(
            Image(R.drawable.group839),
            Image(R.drawable.group2),
            Image(R.drawable.group3),

            )
        recyclerView = binding.myRecyclerView
        adapter = AdapterHorizontalRecycler(images)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}