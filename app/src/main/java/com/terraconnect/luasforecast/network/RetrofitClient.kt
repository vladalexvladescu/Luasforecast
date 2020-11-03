package com.terraconnect.luasforecast.network

import android.content.Context
import com.terraconnect.luasforecast.network.cookiehandler.AddCookiesInterceptor
import com.terraconnect.luasforecast.network.cookiehandler.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


//http://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=sti&encrypt=false
class RetrofitClient {
    fun getRetrofit(context: Context?): Retrofit {
        var client = OkHttpClient()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AddCookiesInterceptor(context))
        builder.addInterceptor(ReceivedCookiesInterceptor(context))
        builder.addInterceptor(logging)
        client = builder.build()

        
        return Retrofit.Builder()
            .baseUrl("https://luasforecasts.rpa.ie/")
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(client)
            .build()
    }
    
}