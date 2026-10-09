package com.m4dm4x100.privatednsswitch

import android.app.Activity
import android.content.ComponentName
import android.os.Bundle
import android.service.quicksettings.TileService
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding(32, 32, 32, 32) }
        layout.addView(TextView(this).apply { text = "Private DNS Switch"; textSize = 24f })
        layout.addView(TextView(this).apply { text = "Add the Private DNS tile to Quick Settings. Android requires a one-time ADB permission grant."; textSize = 16f; setPadding(0, 24, 0, 24) })
        layout.addView(Button(this).apply { text = "Refresh tile"; setOnClickListener { TileService.requestListeningState(this@MainActivity, ComponentName(this@MainActivity, PrivateDnsTileService::class.java)); Toast.makeText(this@MainActivity, "Tile refresh requested", Toast.LENGTH_SHORT).show() } })
        setContentView(layout)
    }
}
