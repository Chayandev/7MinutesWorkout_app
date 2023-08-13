package com.example.need7minute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.need7minute.databinding.ActivityHistoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null
    lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyDao: HistoryDao
    private lateinit var historyDB:HistoryDatabase
    private lateinit var allData:List<HistoryData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        binding?.toolBarHistory?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        // Initialize the ViewModel
        allData = ArrayList()
        historyDB = Room.databaseBuilder(
            applicationContext,
            HistoryDatabase::class.java,
            "history_database"
        ).build()
        historyDao = historyDB.historyDao()
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        binding!!.pgBar.visibility = View.VISIBLE
        // Fetch data using coroutines and update the adapter
        CoroutineScope(Dispatchers.Main).launch {
            val data = withContext(Dispatchers.IO) {
                historyDao.getAllHistory()
            }
            binding!!.pgBar.visibility = View.GONE
            allData = data
            binding!!.pgBar.visibility = View.GONE
            historyAdapter = HistoryAdapter(allData)
            binding!!.recyclerView.adapter = historyAdapter

            Log.i("data", "$allData")
        }
    }


}
