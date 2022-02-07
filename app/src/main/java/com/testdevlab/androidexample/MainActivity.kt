package com.testdevlab.androidexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// Activity is an application component - the UI screen
// TODO: expand on what Activity does
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This shows the view we want it to show
        setContentView(R.layout.activity_main)

        // use logging not println()
        // we will be using the Timber logging library later
        Log.e("", "we got here")
    }
}
