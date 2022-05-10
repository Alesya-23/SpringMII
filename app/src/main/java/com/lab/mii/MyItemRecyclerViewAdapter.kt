package com.lab.mii

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.data.Entry

class MyItemRecyclerViewAdapter(
    private val values: List<Entry>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_data_point, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.xCoordinate.text = item.x.toString()
        holder.yCoordinate.text = item.y.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val xCoordinate: TextView = view.findViewById(R.id.x_coordinate)
        val yCoordinate: TextView = view.findViewById(R.id.y_coordinate)

        override fun toString(): String {
            return super.toString() + " '" + yCoordinate.text + "'"
        }
    }
}