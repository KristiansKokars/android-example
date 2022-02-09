package com.testdevlab.androidexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.databinding.ActivityMainBinding

const val TAG = "example_app"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Activity onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d(TAG, "Destination: " + destination.label)
            navController.backQueue.forEachIndexed { index, navBackStackEntry ->
                Log.d(TAG, "back stack entry $index: ${navBackStackEntry.destination.label.toString()}")
            }
        }
    }
}
