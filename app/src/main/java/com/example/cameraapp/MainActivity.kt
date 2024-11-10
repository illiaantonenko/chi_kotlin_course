package com.example.cameraapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = MutableList(40) { Item("OFF", false) }
        val adapter = ItemAdapter()
        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}