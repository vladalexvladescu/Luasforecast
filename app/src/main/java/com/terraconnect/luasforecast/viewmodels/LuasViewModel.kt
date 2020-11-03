package com.terraconnect.luasforecast.viewmodels
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.terraconnect.luasforecast.data.LuasRoomDatabase
import com.terraconnect.luasforecast.data.RoomSavedDirection
import com.terraconnect.luasforecast.network.ApiResponse
import com.terraconnect.luasforecast.repositories.LuasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LuasViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: LuasRepository
    var allRoomSavedDirectionHistory: LiveData<List<RoomSavedDirection>>
    var retrofitRequestStatuss: LiveData<ApiResponse<String>>

    init {
        //retriving existing data from Room database
        val statusDao = LuasRoomDatabase.getDatabase(application, viewModelScope).statusDao()
        repository = LuasRepository(statusDao)
        allRoomSavedDirectionHistory = repository.allRoomSavedDirectionHistory

        //make the api call
        retrofitRequestStatuss = repository.luasRetrofitResponse
        makeNewCall(application)


    }
    fun makeNewCall(context: Context): LiveData<ApiResponse<String>> {
        val statusDao = LuasRoomDatabase.getDatabase(context, viewModelScope).statusDao()
        var repositoryNew: LuasRepository
        repositoryNew = LuasRepository(statusDao)
        allRoomSavedDirectionHistory = repositoryNew.allRoomSavedDirectionHistory
        //make the api call
        var retrofitRequestStatusNew: LiveData<ApiResponse<String>>
        retrofitRequestStatusNew = repositoryNew.makeNewCallRetrofit()
        return retrofitRequestStatusNew

    }


}
