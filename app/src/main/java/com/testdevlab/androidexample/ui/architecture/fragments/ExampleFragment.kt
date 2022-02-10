package com.testdevlab.androidexample.ui.architecture.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.databinding.FragmentExampleBinding
import com.testdevlab.androidexample.ui.architecture.TAG
import com.testdevlab.androidexample.common.openFragment
import com.testdevlab.androidexample.ui.architecture.viewmodels.MainViewModel

class ExampleFragment : Fragment() {

    private lateinit var binding: FragmentExampleBinding
    private val viewModel: MainViewModel by activityViewModels() // not initialised

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExampleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Instance of MainViewModel - $viewModel")

        displayScore()

        binding.run {
            increaseButton.setOnClickListener {
                viewModel.increaseScore()
                displayScore()
            }
            decreaseButton.setOnClickListener {
                viewModel.decreaseScore()
                displayScore()
            }
            resetButton.setOnClickListener {
                viewModel.resetScore()
                displayScore()
            }
            openNextButton.setOnClickListener {
                openFragment(R.id.navigation_first, R.id.nav_host)
            }
        }
    }

    fun displayScore() {
        binding.userScore.text = viewModel.getUserScore() // initialised
    }
}
