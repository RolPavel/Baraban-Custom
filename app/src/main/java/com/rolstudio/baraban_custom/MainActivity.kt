package com.rolstudio.baraban_custom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.rolstudio.baraban_custom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollBtn.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                    if (!binding.baraban.isSpinning) {
                        binding.baraban.startSpinAnimation()
                    }
            }
            true
        }
    }
//    fun updateTextView(textToShow: String) {
//        binding.textBaraban.text= "text"
//    }
}