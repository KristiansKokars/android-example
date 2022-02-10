package com.testdevlab.androidexample.ui.architecture.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.testdevlab.androidexample.ui.architecture.TAG
import com.testdevlab.androidexample.common.openFragment
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.databinding.FragmentFirstBinding
import com.testdevlab.androidexample.ui.architecture.viewmodels.MainViewModel

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: MainViewModel by activityViewModels()

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
        Log.d(TAG, "Instance of MainViewModel - $viewModel")

        displayScore()

        binding.nextButton.setOnClickListener {
            openFragment(R.id.navigation_second, R.id.nav_host)
        }
    }

    fun displayScore() {
        binding.userScore.text = viewModel.getUserScore() // initialised
    }
}
