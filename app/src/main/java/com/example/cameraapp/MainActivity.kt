package com.example.cameraapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : FragmentActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var sortButton: Button
    private lateinit var arraySizeInput: EditText
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)
        sortButton = findViewById(R.id.sortButton)
        arraySizeInput = findViewById(R.id.arraySizeInput)

        incrementButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        sortButton.setOnClickListener {
            val arraySize = arraySizeInput.text.toString().toIntOrNull() ?: 0
            if (arraySize > 0) {
                sortArrayInBackground(arraySize)
            } else {
                Toast.makeText(this, "Please enter a valid array size", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sortArrayInBackground(size: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val array = IntArray(size) { it }
            val time = measureTimeMillis {
                withContext(Dispatchers.Default) {
                    array.sort()
                }
            }
            Toast.makeText(this@MainActivity, "Array sorted in $time ms", Toast.LENGTH_LONG).show()
        }
    }
}