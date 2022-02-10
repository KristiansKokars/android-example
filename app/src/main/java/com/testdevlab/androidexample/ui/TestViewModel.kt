package com.testdevlab.androidexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testdevlab.androidexample.repositories.Error
import com.testdevlab.androidexample.repositories.Success
import com.testdevlab.androidexample.repositories.TestRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: TestRepository
) : ViewModel() {

    private val _onLoading = MutableSharedFlow<Boolean>(replay = 1)
    private val _onError = MutableSharedFlow<String>(replay = 1)

    val onLoading = _onLoading.asSharedFlow()
    val onError = _onError.asSharedFlow()
    val testItems = repository.testItems

    fun getTestData() = viewModelScope.launch {
        _onLoading.emit(true)
        repository.getTestData().single().let { response ->
            _onLoading.emit(false)
            when (response) {
                is Success -> { /* Ignored */ }
                is Error -> _onError.emit(response.error)
            }
        }
    }

    fun addTestData(data: String) = viewModelScope.launch {
        _onLoading.emit(true)
        repository.addTestData(data).single().let { response ->
            _onLoading.emit(false)
            when (response) {
                is Success -> { /* Ignored */ }
                is Error -> _onError.emit(response.error)
            }
        }
    }

    fun deleteTestData(data: String) = viewModelScope.launch {
        _onLoading.emit(true)
        repository.deleteTestData(data).single().let { response ->
            _onLoading.emit(false)
            when (response) {
                is Success -> { /* Ignored */ }
                is Error -> _onError.emit(response.error)
            }
        }
    }
}
