package com.testdevlab.androidexample.ui.architecture.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.databinding.FragmentExampleBinding
import com.testdevlab.androidexample.ui.architecture.TAG
import com.testdevlab.androidexample.ui.openFragment
import com.testdevlab.androidexample.ui.architecture.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ExampleFragment : Fragment() {

    private lateinit var binding: FragmentExampleBinding
    private val viewModel: MainViewModel by activityViewModels() // not initialised

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExampleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            increaseButton.setOnClickListener {
                viewModel.increaseScore()
            }
            decreaseButton.setOnClickListener {
                viewModel.decreaseScore()
            }
            resetButton.setOnClickListener {
                viewModel.resetScore()
            }
            openNextButton.setOnClickListener {
                openFragment(R.id.navigation_first, R.id.nav_host)
            }
            acceptButton.setOnClickListener {
                viewModel.setNewValue(binding.newValueEdittext.text.toString())
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onScoreUpdated.collect { newValue ->
                    Log.d(TAG, "Flow was collected Example fragment")
                    binding.userScore.text = newValue.toString()
                }
            }
        }
    }
}
