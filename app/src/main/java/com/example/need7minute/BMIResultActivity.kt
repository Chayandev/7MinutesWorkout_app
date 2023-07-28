package com.example.need7minute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.need7minute.databinding.ActivityBmiresultBinding

class BMIResultActivity : AppCompatActivity() {
    private var binding: ActivityBmiresultBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiresultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarBmiResult?.setNavigationOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        val intent: Intent = intent
        val gender = intent.getStringExtra("gender")
        val weight = intent.getFloatExtra("weight", 0.0F)
        val height = intent.getFloatExtra("height", 0.0F)
        val age = intent.getIntExtra("age", 0)
        calculateBMIScore(weight, height)
        binding!!.genderSelected.text = gender
        binding!!.ageSelected.text = age.toString()
        binding!!.weightSelected.text = weight.toString() + "Kg"
        binding!!.heightSelected.text = (height * 100).toString() + "Cm"

        binding!!.recalculateBtn.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onBackPressed() {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
         finishAffinity()
    }
    private fun calculateBMIScore(weight: Float, height: Float) {
        val bmi = (weight) / ((height) * (height))
        val bmi2digit = String.format("%.2f", bmi).toFloat()
        display(bmi2digit)
    }

    private fun display(bmi: Float) {
        var color=0
        val res:String
         when {
            bmi <= 18.5 -> {
                res="UnderWeight"
                color=R.color.underWeight
            }

            bmi in 18.5..24.9 -> {
                res="Healthy"
                color=R.color.healthy
            }

            bmi in 25.0..29.9 -> {
                res="OverWeight"
                color=R.color.overWeight
            }

            else -> {
                res="Obesity"
                color=R.color.obesity
            }
        }
        binding!!.bmiPointTv.text = bmi.toString()
        binding!!.bmiComment.text = res
        binding!!.bmiPointTv.setTextColor(ContextCompat.getColor(this,color))
        binding!!.bmiComment.setTextColor(ContextCompat.getColor(this,color))
    }


}