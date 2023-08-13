package com.example.need7minute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.need7minute.databinding.ActivityFinishBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    private lateinit var historyDao:HistoryDao
    private lateinit var historyDB:HistoryDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        historyDB=Room.databaseBuilder(applicationContext, HistoryDatabase::class.java, "history_database").build()
        historyDao = historyDB.historyDao()

        saveHistory()
        setSupportActionBar(binding?.toolBarFinishExcersise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarFinishExcersise?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding!!.doneAnim.animate()
        binding!!.fHistoryBtn.setOnClickListener {
            val intent=Intent(this,HistoryActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding!!.finishBtn.setOnClickListener {
            finish()

        }
    }
    private fun saveHistory() {
        val currentDateTime = Date()
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val formattedDate = dateFormatter.format(currentDateTime)
        val formattedTime = timeFormatter.format(currentDateTime)
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val historyData =
                    HistoryData(date = formattedDate.toString(), time = formattedTime.toString())
                historyDao.insert(historyData)
            }
        }

    }
}