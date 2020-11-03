package com.terraconnect.luasforecast.util

import android.util.Log
import com.google.gson.Gson
import com.terraconnect.luasforecast.network.ApiResponse
import com.terraconnect.luasforecast.network.model.luasdata.LuasServiceResponseData
import fr.arnaudguyon.xmltojsonlib.XmlToJson

class ConvertResponse {
    internal fun convertXMltoJSON(xml:ApiResponse<String>): LuasServiceResponseData {
        var xmls = xml.toString()
        var xmlString =xmls.substring(24,xmls.length-1)
        var xmlToJson =  XmlToJson.Builder(xmlString).build();
        // convert XML to a JSONObject
        var jsonObject = xmlToJson.toJson();
        val gson = Gson()
        val obj: LuasServiceResponseData =
            gson.fromJson<LuasServiceResponseData>(jsonObject.toString(), LuasServiceResponseData::class.java)
        return obj
    }
}