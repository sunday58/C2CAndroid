package com.app.c2candroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


fun checkInternet(context: Context): Boolean {
    val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    val mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    return wifi!!.isConnected || mobile!!.isConnected
}