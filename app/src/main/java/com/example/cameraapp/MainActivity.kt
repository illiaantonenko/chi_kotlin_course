package com.example.cameraapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.cameraapp.network.NetworkLayer
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    private lateinit var fetchDataButton: Button
    private lateinit var dataTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchDataButton = findViewById(R.id.fetchDataButton)
        dataTextView = findViewById(R.id.dataTextView)

        fetchDataButton.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val posts = NetworkLayer.apiService.getPosts()
                dataTextView.text = posts.joinToString("\n\n") { it.title }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}