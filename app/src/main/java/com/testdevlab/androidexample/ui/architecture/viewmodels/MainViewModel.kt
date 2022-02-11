package com.testdevlab.androidexample.ui.architecture.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.testdevlab.androidexample.R
import com.testdevlab.androidexample.models.User
import com.testdevlab.androidexample.ui.architecture.TAG
import kotlinx.coroutines.flow.*
import java.lang.NumberFormatException

class MainViewModel: ViewModel() {

    private val _onScoreUpdated = MutableStateFlow(0)
    private val _onError = MutableSharedFlow<Int>(replay = 1)

    val onError = _onError.asSharedFlow()
    val onScoreUpdated = _onScoreUpdated.asStateFlow()

    val user = User("Me", 69)

    fun increaseScore() {
       _onScoreUpdated.value += 1
    }

    fun decreaseScore() {
        _onScoreUpdated.value -= 1
    }

    fun resetScore() {
        _onScoreUpdated.value = 0
    }

    fun setNewValue(newValue: String) {
        try {
            _onScoreUpdated.value = newValue.toInt()
        } catch (e: NumberFormatException) {
            _onError.tryEmit(R.string.fragment_example_error)
        }
    }

    fun showError() {
        _onError.tryEmit(R.string.fragment_one_error)
    }

    override fun onCleared() {
        Log.d(TAG, "$this instance of MainViewModel is cleared")
        super.onCleared()
    }
}
