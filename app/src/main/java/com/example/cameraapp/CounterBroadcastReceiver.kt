package com.example.cameraapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class CounterBroadcastReceiver : BroadcastReceiver() {
    private var listener: ((Int) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val counter = intent?.getIntExtra("counter_value", 0) ?: 0
        listener?.invoke(counter)
    }

    fun setOnCounterUpdateListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    companion object {
        fun getIntentFilter(): IntentFilter {
            return IntentFilter("com.example.cameraapp.COUNTER_UPDATE")
        }
    }
}