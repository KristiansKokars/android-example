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
import com.testdevlab.androidexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        Log.d(TAG, "Fragment one: onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Fragment one: onViewCreated")

        binding.nextButton.setOnClickListener {
            openFragment(R.id.navigation_second, R.id.nav_host)
        }
    }
}
