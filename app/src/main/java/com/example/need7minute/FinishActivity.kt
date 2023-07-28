package com.example.need7minute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.need7minute.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding?.toolBarFinishExcersise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarFinishExcersise?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding!!.doneAnim.animate()
        binding!!.finishBtn.setOnClickListener {
            finish()

        }
    }
}