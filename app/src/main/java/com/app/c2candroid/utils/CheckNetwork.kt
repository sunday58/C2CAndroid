package com.app.c2candroid.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import com.app.c2candroid.R


fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected){
            return true
        }
    }

    return false
}

fun Context.alertInternet(context: Context) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.window?.decorView?.setBackgroundColor(Color.TRANSPARENT)
    dialog.setContentView(R.layout.dialog_internet_alert)

    dialog.findViewById<AppCompatTextView>(R.id.tvMsg).text = getString(R.string.offline_message)

    val tvCancel = dialog.findViewById<AppCompatTextView>(R.id.tvCancel)

    tvCancel.text = getString(R.string.cansle)

    tvCancel.setOnClickListener {

        dialog.cancel()
    }

    val tvConfirm = dialog.findViewById<AppCompatTextView>(R.id.tvConfirm)

    tvConfirm.text = getString(R.string.settings)

    tvConfirm.setOnClickListener {

        val settingIntent = Intent(Settings.ACTION_SETTINGS)

        context.startActivity(settingIntent)

        dialog.cancel()
    }

    dialog.show()
}