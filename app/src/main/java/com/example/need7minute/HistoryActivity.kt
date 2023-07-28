package com.example.need7minute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.need7minute.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarHistory?.setNavigationOnClickListener {
           onBackPressedDispatcher.onBackPressed()
        }

    }
}