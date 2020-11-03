package com.terraconnect.luasforecast.repositories

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.terraconnect.App
import com.terraconnect.luasforecast.dao.StatusDAO
import com.terraconnect.luasforecast.data.RoomSavedDirection
import com.terraconnect.luasforecast.network.ApiResponse
import com.terraconnect.luasforecast.network.RetrofitClient
import com.terraconnect.luasforecast.network.model.LuasApi
import com.terraconnect.luasforecast.util.ConvertDateToTime
import kotlinx.android.synthetic.main.activity_main.*


class LuasRepository(private val statusDAO: StatusDAO) {

    //the logic to querry the database
    val allRoomSavedDirectionHistory: LiveData<List<RoomSavedDirection>> = statusDAO.getStatusHistory()

    //check to see if there is internet connection
    //  if(NetworkStatuss.isInternetAvailable()){
    //if true then we can perform the request to retrive data from the API
    var context: Context? = App.context
    var luasService = RetrofitClient().getRetrofit(context).create(LuasApi::class.java)
    //first request on app launch
    //https://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=sti&encrypt=false


    var luasRetrofitResponse:LiveData<ApiResponse<String>> = luasService.getStatussResponse("forecast","sti",false)

    fun makeNewCallRetrofit():LiveData<ApiResponse<String>>{
        if(ConvertDateToTime.isInbountAndStillorgan()){

             var context: Context? = App.context
             var luasService = RetrofitClient().getRetrofit(context).create(LuasApi::class.java)
             var luasRetrofitResponseNew = luasService.getStatussResponse("forecast","sti",false)
            return luasRetrofitResponseNew
        }else{

            var context: Context? = App.context
            var luasService = RetrofitClient().getRetrofit(context).create(LuasApi::class.java)
            var luasRetrofitResponseNew = luasService.getStatussResponse("forecast","mar",false)
            return luasRetrofitResponseNew
        }

    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(roomSavedDirection: RoomSavedDirection) {
        statusDAO.insert(roomSavedDirection)
    }
}