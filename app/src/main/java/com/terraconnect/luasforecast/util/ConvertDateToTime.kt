package com.terraconnect.luasforecast.util

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ConvertDateToTime {
    companion object{
        fun convertDate(dateSt :String):String{
            var dateFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            var d = dateFormat.parse(dateSt)
            val date = Calendar.getInstance().time
            val dateFormat1: DateFormat = SimpleDateFormat("HH:mm")
            val strDate = dateFormat1.format(date)
            return strDate

        }
        fun isInbountAndStillorgan ():Boolean{
            val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)
            if(hour>=12 && hour<=23 ){
                if( minute >=1 && minute <=59){
                    return true
                }else{
                   return false
                }

            }else{
                return false
            }
        }

    }

}