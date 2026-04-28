package com.example.airplane_mode
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.TextView

class AirplaneModeReceiver(private val textView: TextView): BroadcastReceiver() {

    override fun onReceive(context: Context,intent: Intent)
    {
        val ison= Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON
        )!=0

        if(ison)
        {
            textView.text="Airplane Mode is On"
        }
        else
        {
            textView.text="Airplane Mode Off"
        }
    }

}



