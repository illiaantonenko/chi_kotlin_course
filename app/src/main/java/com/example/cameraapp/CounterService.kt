package com.example.cameraapp

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CounterService : Service() {
    private var counter = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun getCounter(): Int {
        return counter
    }

    fun incrementCounter() {
        counter++
    }
}