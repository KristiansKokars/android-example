package com.testdevlab.androidexample.navigation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testdevlab.androidexample.navigation.TAG
import com.testdevlab.androidexample.navigation.openFragment
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        Log.d(TAG, "Fragment two onCreateView")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Fragment two onViewCreated")


        binding.goBackButton.setOnClickListener {
            openFragment(R.id.navigation_first, R.id.nav_host)
        }

        binding.nextButton.setOnClickListener {
            openFragment(R.id.navigation_third, R.id.nav_host)
        }
    }
}
