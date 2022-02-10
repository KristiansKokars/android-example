package com.testdevlab.androidexample.ui.architecture.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.testdevlab.androidexample.ui.architecture.TAG

class MainViewModel: ViewModel() {

    private var score: Int = 0

    fun getUserScore() = score.toString()

    fun increaseScore() {
        score++
    }

    fun decreaseScore() {
        score--
    }

    fun resetScore() {
        score = 0
    }

    override fun onCleared() {
        Log.d(TAG, "$this instance of MainViewModel is cleared")
        super.onCleared()
    }
}
