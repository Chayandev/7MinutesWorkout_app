package com.example.need7minute

import android.app.backup.BackupAgent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.need7minute.databinding.ActivityBmiCalculatorBinding

class BmiCalculatorActivity : AppCompatActivity() {
    private var gender: String? = null
    private var heightUnit: String? = null
    private var weightUnit: String? = null
    private var binding: ActivityBmiCalculatorBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarBmi?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
//        //weight spinner setup
        setupWeightSpinner()
//        //height spinner setup
        setUpHeightSpinner()

        //setup weight inputs
        setUpWeightInput()
        //setup height inputs
        setUpHeightInputs()
        //setUp age input
        setUpAgeInput()

        //Gender select
        binding!!.genderMan.setOnClickListener {

            binding!!.genderWoman.background = ContextCompat.getDrawable(this, R.drawable.gender_bg)
            DrawableCompat.setTint(
                binding!!.womanImg.drawable, ContextCompat.getColor(
                    applicationContext, R.color.white
                )
            )

            binding!!.genderMan.background =
                ContextCompat.getDrawable(this, R.drawable.gender_selcted_change_bg)
            DrawableCompat.setTint(
                binding!!.manImg.drawable, ContextCompat.getColor(
                    applicationContext, R.color.yellow
                )
            )

            gender = "Male"
        }
        binding!!.genderWoman.setOnClickListener {

            binding!!.genderMan.background = ContextCompat.getDrawable(this, R.drawable.gender_bg)
            DrawableCompat.setTint(
                binding!!.manImg.drawable, ContextCompat.getColor(
                    applicationContext, R.color.white
                )
            )

            binding!!.genderWoman.background =
                ContextCompat.getDrawable(this, R.drawable.gender_selcted_change_bg)
            DrawableCompat.setTint(
                binding!!.womanImg.drawable, ContextCompat.getColor(
                    applicationContext, R.color.yellow
                )
            )

            gender = "Female"
        }

        //calculate the result
        binding?.calculateBtn?.setOnClickListener {
            var cnt = 0
            var height:Float= 0.0F
            var weight:Float=0.0F
            var age:Int=0
            val intent = Intent(this, BMIResultActivity::class.java)
            if (gender == null) {
                Toast.makeText(this, "Please select gender.", Toast.LENGTH_SHORT).show()
            } else {
                cnt++
            }
            if (binding!!.inputHeightTv.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter Height.", Toast.LENGTH_SHORT).show()
            } else {
                height = binding!!.inputHeightTv.text.toString().toFloat()
                height = when (heightUnit) {
                    "Cm" -> height / 100
                    "Feet" -> (height * 0.3048).toFloat()
                    else -> height
                }
                cnt++
            }
            if (binding!!.inputWeightTv.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter Weight.", Toast.LENGTH_SHORT).show()
            } else {
                 weight = binding!!.inputWeightTv.text.toString().toFloat()
                weight = when (weightUnit) {
                    "Pounds" -> (weight * 0.453592).toFloat()
                    else -> weight
                }
                cnt++
            }
            if (binding!!.inputAgeTv.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter Age.", Toast.LENGTH_SHORT).show()
            } else {
                 age=binding!!.inputAgeTv.text.toString().toInt()
                cnt++
            }
            if(cnt==4) {
                intent.putExtra("gender",gender)
                intent.putExtra("height",String.format("%.2f",height).toFloat())
                intent.putExtra("weight",String.format("%.2f",weight).toFloat())
                intent.putExtra("age",age)
                startActivity(intent)
            }
        }
    }


    private fun setUpAgeInput() {
        binding?.plusABtn?.setOnClickListener {
            if (binding!!.inputAgeTv.text.toString().isEmpty())
                binding!!.inputAgeTv.setText("0")
            binding!!.inputAgeTv.setText("${binding!!.inputAgeTv.text.toString().toInt() + 1}")
        }
        binding?.minusABtn?.setOnClickListener {
            if (binding!!.inputAgeTv.text.toString()
                    .isEmpty() || binding!!.inputAgeTv.text.toString().toInt() < 1
            ) {
                Toast.makeText(this, "Your Age can't be negative", Toast.LENGTH_SHORT).show()
                binding!!.inputAgeTv.setText("0")
            } else
                binding!!.inputAgeTv.setText("${binding!!.inputAgeTv.text.toString().toInt() - 1}")
        }
    }

    private fun setUpHeightInputs() {
        binding?.plusHBtn?.setOnClickListener {
            if (binding!!.inputHeightTv.text.toString().isEmpty())
                binding!!.inputHeightTv.setText("0.0")
            binding?.inputHeightTv?.setText(
                String.format(
                    "%.2f",
                    binding!!.inputHeightTv.text.toString().toFloat() + 1
                )
            )
        }
        binding?.minusHBtn?.setOnClickListener {
            if (binding!!.inputHeightTv.text.toString()
                    .isEmpty() || binding!!.inputHeightTv.text.toString().toFloat() < 1.00
            ) {
                binding!!.inputHeightTv.setText("0.0")
                Toast.makeText(this, "Your Height can't be Negative.", Toast.LENGTH_SHORT).show()
            } else
                binding?.inputHeightTv?.setText(
                    String.format(
                        "%.2f",
                        binding!!.inputHeightTv.text.toString().toFloat() - 1
                    )
                )
        }
    }

    private fun setUpWeightInput() {
        binding?.plusWBtn?.setOnClickListener {
            if (binding!!.inputWeightTv.text.toString().isEmpty())
                binding!!.inputWeightTv.setText("0.0")
            binding?.inputWeightTv?.setText(
                String.format(
                    "%.2f",
                    binding!!.inputWeightTv.text.toString().toFloat() + 1
                )
            )
        }
        binding?.minusWBtn?.setOnClickListener {
            if (binding!!.inputWeightTv.text.toString()
                    .isEmpty() || binding!!.inputWeightTv.text.toString().toFloat() < 1.00
            ) {
                binding!!.inputWeightTv.setText("0.0")
                Toast.makeText(this, "Your Height can't be Negative.", Toast.LENGTH_SHORT).show()
            } else
                binding?.inputWeightTv?.setText(
                    String.format(
                        "%.2f",
                        binding!!.inputWeightTv.text.toString().toFloat() - 1
                    )
                )
        }
    }

    // height spinner
    private fun setUpHeightSpinner() {
        val spinner = binding?.heightSpinner
        val heightUnits = resources.getStringArray(R.array.height_array)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item_view, heightUnits
        )
        spinner?.adapter = adapter

        spinner?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                heightUnit = heightUnits[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    //weight spinner
    private fun setupWeightSpinner() {
        val spinner = binding?.weightSpinner
        val weightUnits = resources.getStringArray(R.array.weight_array)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item_view, weightUnits
        )
        spinner?.adapter = adapter

        spinner?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                weightUnit = weightUnits[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDestroy() {
        binding!!.genderWoman.background = ContextCompat.getDrawable(this, R.drawable.gender_bg)
        DrawableCompat.setTint(
            binding!!.womanImg.drawable, ContextCompat.getColor(
                applicationContext, R.color.white
            )
        )

        binding!!.genderMan.background =
            ContextCompat.getDrawable(this, R.drawable.gender_bg)
        DrawableCompat.setTint(
            binding!!.manImg.drawable, ContextCompat.getColor(
                applicationContext, R.color.white
            )
        )
        super.onDestroy()
    }
}

