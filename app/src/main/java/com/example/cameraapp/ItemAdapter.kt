package com.example.cameraapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
        val switch: Switch = itemView.findViewById(R.id.itemSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.textView.text = if (item.isChecked) "ON" else "OFF"
        holder.textView.setTextColor(if (item.isChecked) Color.GREEN else Color.RED)
        holder.switch.isChecked = item.isChecked

        holder.switch.setOnCheckedChangeListener(null) // Remove previous listener
        holder.switch.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            holder.textView.text = if (isChecked) "ON" else "OFF"
            holder.textView.setTextColor(if (isChecked) Color.GREEN else Color.RED)
        }
    }
}