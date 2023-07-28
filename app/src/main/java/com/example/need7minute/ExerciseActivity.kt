package com.example.need7minute

import android.app.Dialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.need7minute.databinding.ActivityExerciseBinding
import com.example.need7minute.databinding.ActivityMainBinding
import com.example.need7minute.databinding.CustomDialogOnBackpressedActivityBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityExerciseBinding

    //rest time
    private var resTimer: CountDownTimer? = null
    private var restProgress = 0

    //exercise timer
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


    //exercises
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1
    private var remainingTime: Long? = null
    private var totalTime: Long = 30000
    private var pauseOffSet: Long = 0
    private var isPushed: Boolean = false

    //Text to speech
    private var tts: TextToSpeech? = null

    //setting recycler adapter view
    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //To set the toolbar
        setSupportActionBar(binding.toolBarExcersise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding.toolBarExcersise.setNavigationOnClickListener {
            customDialogCheck()
        }
        tts = TextToSpeech(this, this)
        exerciseList = Constants.defaultExerciseList()
        //call the set progress bar
        setRestView()
        setUpExerciseStatusRcView()
    }

    override fun onBackPressed() {
        customDialogCheck()
       // super.onBackPressed()
    }
    private fun customDialogCheck() {
        val customDialog=Dialog(this)
        val dialogBinding=CustomDialogOnBackpressedActivityBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.yesBtn.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.noBtn.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setUpExerciseStatusRcView() {
        binding.exerciseStatusView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding.exerciseStatusView.adapter = exerciseAdapter

    }

    //set the ready timer
    private fun setRestView() {
        binding.restView.visibility = View.VISIBLE
        binding.restTxt.visibility = View.VISIBLE
        binding.upcomingExlb.visibility = View.VISIBLE
        binding.upex.visibility = View.VISIBLE
        binding.tvExerciseName.visibility = View.INVISIBLE
        binding.exerciseLayout.visibility = View.INVISIBLE
        binding.animBtn.visibility = View.INVISIBLE
        binding.animationView.visibility = View.INVISIBLE

//        binding.exerciseAnim.visibility= View.INVISIBLE
        if (resTimer != null) {
            resTimer?.cancel()
            restProgress = 0
        }
        speakOut("Take Rest for Ten Second , and get Ready for the  Upcoming Exercise ${exerciseList!![currentExercisePosition + 1].getExName()}")
        binding.upex.text = exerciseList!![currentExercisePosition + 1].getExName()
        setRestProgressBar()
    }

    //set the exercise timer
    private fun setExerciseView() {
        binding.restView.visibility = View.INVISIBLE
        binding.restTxt.visibility = View.INVISIBLE
        binding.upex.visibility = View.INVISIBLE
        binding.upcomingExlb.visibility = View.INVISIBLE
        binding.tvExerciseName.visibility = View.VISIBLE
        binding.exerciseLayout.visibility = View.VISIBLE
        binding.animBtn.visibility = View.VISIBLE
        binding.animationView.visibility = View.VISIBLE
//        binding.exerciseAnim.visibility= View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        speakOut("Now the Exercise is ${exerciseList!![currentExercisePosition].getExName()}")
        binding.animationView.setImageResource(exerciseList!![currentExercisePosition].getImgName())
        binding.tvExerciseName.text = exerciseList!![currentExercisePosition].getExName()
        setExerciseProgressBar(pauseOffSet)

    }

    //setting the progressbar
    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress
        resTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.timerTxt.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                resetTime()
                currentExercisePosition++
                //notifying rv
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged() //that will call onViewBinderHolder again
                setExerciseView()
            }

        }.start()
    }

    //setting the progressbar and title for upcoming exercise
    private fun setExerciseProgressBar(Time: Long) {
        binding.exerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(totalTime - pauseOffSet * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.animBtn.setOnClickListener {
                    if (binding.animBtn.text == "PAUSE") {
                        remainingTime = millisUntilFinished
                        pauseExercise()
                        binding.animBtn.text = "RESUME"
                    } else {
                        resumeExercise()
                        binding.animBtn.text = "PAUSE"
                    }
                }
                Log.i("timeGOne", "$millisUntilFinished")
                exerciseProgress++
                pauseOffSet = totalTime / 1000 - millisUntilFinished / 1000
                binding.exerciseProgressBar.progress = 30 - exerciseProgress
                binding.exerciseTimerTxt.text = (30 - exerciseProgress).toString()

            }

            override fun onFinish() {
//                Snackbar.make(
//                    binding.exAcitvity,
//                    "The 30sec for this exercise is over.",
//                    Snackbar.LENGTH_SHORT
//                ).show()
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    //notifying the rv
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setRestView()
                }
                else {
                    finish()
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }

    private fun pauseExercise() {
        exerciseTimer!!.cancel()
        Log.i("time_remain", "$remainingTime")
        Log.i("pg", "$exerciseProgress")
    }

    private fun resumeExercise() {
        setExerciseProgressBar(pauseOffSet)
    }

    private fun resetTime() {
        pauseOffSet = 0
    }

    override fun onDestroy() {
        super.onDestroy()
        if (resTimer != null) {
            resTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
            totalTime = 30000
            remainingTime = null
        }
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
//        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("TTS", "The Language is not supported")
            }
        } else {
            Log.e("TTs", "Initialization Failed")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}