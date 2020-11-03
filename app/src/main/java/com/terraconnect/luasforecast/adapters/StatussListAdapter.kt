package com.terraconnect.luasforecast.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.terraconnect.luasforecast.R
import com.terraconnect.luasforecast.data.RoomSavedDirection
import com.terraconnect.luasforecast.network.model.luasdata.Direction
import com.terraconnect.luasforecast.network.model.luasdata.Tram


class StatussListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<StatussListAdapter.StatussViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var statussHistory = mutableListOf<RoomSavedDirection>()
    private lateinit var aux :  RoomSavedDirection

    inner class StatussViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemViewTime: TextView = itemView.findViewById(R.id.textView_due_min)
        val itemViewDest: TextView = itemView.findViewById(R.id.textView_destination)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatussViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return StatussViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StatussViewHolder, position: Int) {
        val current = statussHistory[position]
        holder.itemViewDest.text = current.destination
        holder.itemViewTime.text = current.dueMins
    }

    internal fun setHistory(roomSavedDirectionHistory: List<Tram>) {
        this.statussHistory.clear()
        for(i in roomSavedDirectionHistory){
            aux  = RoomSavedDirection(i.destination,i.dueMins.toString())
            this.statussHistory.add(aux)
        }

        notifyDataSetChanged()

    }
    override fun getItemCount() = statussHistory.size
}