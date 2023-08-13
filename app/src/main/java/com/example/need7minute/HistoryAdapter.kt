package com.example.need7minute

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val dataSet:List<HistoryData>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.history_item_view,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val item=dataSet[position]

        holder.dateTv.text=item.date
        holder.timeTv.text=item.time
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val dateTv:TextView=view.findViewById(R.id.date)
        val timeTv:TextView=view.findViewById(R.id.time)
    }
}