package com.example.cameraapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var navigateButton: Button
    private var counter = 0

    private val counterReceiver = CounterBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)
        navigateButton = findViewById(R.id.navigateButton)

        incrementButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
            sendCounterUpdate()
        }

        navigateButton.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(CounterFragment::class.java.simpleName)
            if (fragment == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CounterFragment.newInstance(counter), CounterFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            } else {
                (fragment as CounterFragment).updateCounter(counter)
            }
        }

        counterReceiver.setOnCounterUpdateListener { newCounter ->
            counter = newCounter
            counterTextView.text = counter.toString()
        }

        startService(Intent(this, CounterService::class.java))
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(counterReceiver, CounterBroadcastReceiver.getIntentFilter(),
            RECEIVER_EXPORTED
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(counterReceiver)
    }

    private fun sendCounterUpdate() {
        val intent = Intent("com.example.cameraapp.COUNTER_UPDATE")
        intent.putExtra("counter_value", counter)
        Log.d("MainActivity", "Sending counter update: $counter")
        sendBroadcast(intent)
    }
}