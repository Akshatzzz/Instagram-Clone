package com.example.instagramclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val scope = MainScope() + CoroutineExceptionHandler{ _, exception ->
        run { Log.d(TAG, "$exception") }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToNextActivity()
    }
    private fun navigateToNextActivity() {
        scope.launch {
            delay(3000)
            openSignUpActivity()
        }
    }

    private fun openSignUpActivity() {
        try {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}