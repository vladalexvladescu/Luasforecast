package com.terraconnect.luasforecast

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.terraconnect.luasforecast.adapters.StatussListAdapter
import com.terraconnect.luasforecast.network.model.luasdata.Tram
import com.terraconnect.luasforecast.util.ConvertDateToTime
import com.terraconnect.luasforecast.util.ConvertDateToTime.Companion.convertDate
import com.terraconnect.luasforecast.util.ConvertResponse
import com.terraconnect.luasforecast.viewmodels.LuasViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var luasViewModel: LuasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        precesssResponse()

        fab.setOnClickListener {
            progressBar.setVisibility(View.VISIBLE)
            precesssResponse()
        }

    }

    fun precesssResponse (){

        val convertResponse = ConvertResponse()
        //change withe trams
        var trams : List<Tram>
        val recyclerViewInbount = findViewById<RecyclerView>(R.id.recyclerview_inbound)
        val adapterInbount = StatussListAdapter(this)
        recyclerViewInbount.adapter = adapterInbount
        recyclerViewInbount.layoutManager = LinearLayoutManager(this)
        luasViewModel = ViewModelProvider(this).get(LuasViewModel::class.java)
        luasViewModel.retrofitRequestStatuss.observe(this, Observer { statussHistory ->
            statussHistory?.let {
                //0 = inbound , 1 = outbound
                if(ConvertDateToTime.isInbountAndStillorgan()){
                    direction.setText("Inbound")
                }else{
                    direction.setText("Outbound")
                }
                var response = convertResponse.convertXMltoJSON(it)
                trams = response.stopInfo.direction.get(0)?.tram as List<Tram>
                textView_station_main.setText(response.stopInfo.stop)
                textView_name_status.setText(response.stopInfo.message)
                var dateSt = convertDate(response.stopInfo.created)
                textView_name_station.setText(dateSt)
                adapterInbount.setHistory(trams) }
            progressBar.setVisibility(View.INVISIBLE)
        })

    }

}