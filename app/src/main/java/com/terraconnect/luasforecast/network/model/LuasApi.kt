package com.terraconnect.luasforecast.network.model

import androidx.lifecycle.LiveData
import com.terraconnect.luasforecast.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*xml/get.ashx?action=forecast&stop=sti&encrypt=false
* */
interface LuasApi {
    @GET("xml/get.ashx?")
    fun getStatussResponse(@Query("action")action: String ,
                           @Query("stop")stop: String,
                           @Query("encrypt")encrypt: Boolean): LiveData<ApiResponse<String>>
}