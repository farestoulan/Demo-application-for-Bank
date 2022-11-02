package com.example.roomapp.presentation.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.roomapp.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val bottomNavigationView =
//            findViewById<BottomNavigationView>(R.id.bottomNavigationViewClientHome)
//        val navController = findNavController(R.id.fragmentContainer)
//        bottomNavigationView.setupWithNavController(navController)
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.clientHome -> showBottomNav()
//                R.id.withdrawStatus -> showBottomNav()
//                R.id.depositStatus -> showBottomNav()
//                R.id.deposit -> showBottomNav()
//                R.id.withdraw -> showBottomNav()
//                else -> hideBottomNav()
//            }
//        }
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.clientHome -> showItemBottomNav()
//                R.id.employeeHome -> hideItemBottomNav()
//            }
//        }
//
//    }
//
//    private fun showBottomNav() {
//        bottomNavigationView.visibility = View.VISIBLE
//
//    }

//    private fun hideBottomNav() {
//        bottomNavigationView.visibility = View.GONE
//
//    }

//    private fun hideItemBottomNav() {
//        bottomNavigationView.menu.findItem(R.id.withdrawStatus).isVisible = false
//        bottomNavigationView.menu.findItem(R.id.depositStatus).isVisible = false
//        bottomNavigationView.menu.findItem(R.id.clientHome).isVisible = false
//        bottomNavigationView.menu.findItem(R.id.employeeHome).isVisible = true
//
//
//    }

//    private fun showItemBottomNav() {
//
//
//        bottomNavigationView.menu.findItem(R.id.withdrawStatus).isVisible = true
//        bottomNavigationView.menu.findItem(R.id.depositStatus).isVisible = true
//        bottomNavigationView.menu.findItem(R.id.clientHome).isVisible = true
//        bottomNavigationView.menu.findItem(R.id.employeeHome).isVisible = false
//
//
//    }

}
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}