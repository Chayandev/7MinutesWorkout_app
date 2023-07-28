package com.example.need7minute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.need7minute.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLayout.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.bmiBtn.setOnClickListener{
            val intent=Intent(this,BmiCalculatorActivity::class.java)
            startActivity(intent)
        }
        binding.historyBtn.setOnClickListener{
            val intent=Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}