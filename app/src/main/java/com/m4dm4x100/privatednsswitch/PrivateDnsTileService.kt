package com.m4dm4x100.privatednsswitch

import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast

class PrivateDnsTileService : TileService() {
    private fun mode(): String = Settings.Global.getString(contentResolver, "private_dns_mode") ?: "opportunistic"
    private fun specifier(): String? = Settings.Global.getString(contentResolver, "private_dns_specifier")

    override fun onStartListening() { super.onStartListening(); updateTile() }

    override fun onClick() {
        super.onClick()
        if (!Settings.canWrite(this)) {
            Toast.makeText(this, "Grant WRITE_SECURE_SETTINGS using ADB; see README", Toast.LENGTH_LONG).show()
            updateTile()
            return
        }
        val next = if (mode() == "off") { if (!specifier().isNullOrBlank()) "hostname" else "opportunistic" } else "off"
        val ok = Settings.Global.putString(contentResolver, "private_dns_mode", next)
        if (!ok) Toast.makeText(this, "Could not change Private DNS. Check ADB permission.", Toast.LENGTH_LONG).show()
        updateTile()
    }

    private fun updateTile() {
        val tile = qsTile ?: return
        val enabled = mode() != "off"
        tile.state = if (enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.label = "Private DNS"
        tile.subtitle = if (enabled) "On" else "Off"
        tile.contentDescription = "Private DNS ${if (enabled) "on" else "off"}"
        tile.updateTile()
    }
}
