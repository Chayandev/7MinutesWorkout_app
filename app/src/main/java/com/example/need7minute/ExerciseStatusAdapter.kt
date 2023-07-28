package com.example.need7minute

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.need7minute.databinding.RecyclerItemViewBinding
import kotlin.math.log

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(binding: RecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        Log.e("enter_int_change","Here seting up")
        when {
            model.getIsSelected() -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.item_selected_bg)
                holder.tvItem.setTextColor(Color.BLACK)
            }
            model.getIsCompleted() -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.item_completed_bg)
            }
            else -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.r_itembg)
                holder.tvItem.setTextColor(Color.BLACK)
            }
        }
    }

}