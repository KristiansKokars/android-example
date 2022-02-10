package com.testdevlab.androidexample.ui.threading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.testdevlab.androidexample.databinding.ActivityThreadBinding
import com.testdevlab.androidexample.ui.architecture.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ThreadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "1")
        runWork()
        Log.d(TAG, "2")
    }

    fun runWork() = runBlocking {
        launch {
            doWork()
            doWork3()
        }

        launch {
            doWork2()
        }

        Log.d(TAG, "3")
    }

    suspend fun doWork() {
        delay(1000)
        Log.d(TAG, "4")
    }

    suspend fun doWork2() {
        delay(1000)
        Log.d(TAG, "5")
    }

    suspend fun doWork3() {
        delay(1000)
        Log.d(TAG, "6")
    }
}
