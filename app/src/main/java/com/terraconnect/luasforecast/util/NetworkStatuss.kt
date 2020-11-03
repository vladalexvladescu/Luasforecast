package com.terraconnect.luasforecast.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class NetworkStatuss
{
    companion object{
        fun isConnected(context: Context): Boolean {
            val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return if (activeNetwork != null && activeNetwork.isConnected) {
                try {
                    val url = URL("https://www.google.com/")
                    val urlc: HttpURLConnection = url.openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "test")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.setConnectTimeout(1000) // mTimeout is in seconds
                    urlc.connect()
                    if (urlc.getResponseCode() === 200) {
                        true
                    } else {
                        false
                    }
                } catch (e: IOException) {
                    Log.i("warning", "Error checking internet connection", e)
                    false
                }
            } else false
        }

    }


}