package com.terraconnect.luasforecast.network.cookiehandler

import android.content.Context
import android.preference.PreferenceManager
import com.terraconnect.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ReceivedCookiesInterceptor(context: Context?) : Interceptor {
    private val context: Context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies =
                PreferenceManager.getDefaultSharedPreferences(context).getStringSet(
                    "PREF_COOKIES",
                    HashSet()
                ) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies!!.add(header)
            }
            val memes =
                PreferenceManager.getDefaultSharedPreferences(context).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }

    init {
        this.context = App.context
    }

}