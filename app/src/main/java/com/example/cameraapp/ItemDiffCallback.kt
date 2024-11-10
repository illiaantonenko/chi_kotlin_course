package com.example.cameraapp

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}