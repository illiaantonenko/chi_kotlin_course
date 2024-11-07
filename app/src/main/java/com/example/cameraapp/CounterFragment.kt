package com.example.cameraapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var passDataButton: Button
    private var counter = 0

    private val counterReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val newCounter = intent?.getIntExtra("counter_value", 0) ?: 0
            Log.d("CounterFragment", "Received counter update: $newCounter")
            counter = newCounter
            counterTextView.text = counter.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)
        counterTextView = view.findViewById(R.id.counterTextView)
        incrementButton = view.findViewById(R.id.incrementButton)
        passDataButton = view.findViewById(R.id.passDataButton)

        counter = arguments?.getInt(ARG_COUNTER) ?: 0
        counterTextView.text = counter.toString()

        incrementButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        passDataButton.setOnClickListener {
            sendCounterUpdate()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        requireContext().registerReceiver(counterReceiver, IntentFilter("com.example.cameraapp.COUNTER_UPDATE"),
            Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(counterReceiver)
    }

    private fun sendCounterUpdate() {
        val intent = Intent("com.example.cameraapp.COUNTER_UPDATE")
        intent.putExtra("counter_value", counter)
        Log.d("CounterFragment", "Sending counter update: $counter")
        requireContext().sendBroadcast(intent)
    }

    fun updateCounter(newCounter: Int) {
        counter = newCounter
        counterTextView.text = counter.toString()
    }

    companion object {
        private const val ARG_COUNTER = "counter"

        fun newInstance(counter: Int) = CounterFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_COUNTER, counter)
            }
        }
    }
}