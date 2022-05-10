package com.lab.mii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry

class DataPointsActivity : AppCompatActivity() {
    private var listPoints: ArrayList<Entry> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_points)
        listPoints = intent.getParcelableArrayListExtra<Entry>(LIST_POINTS) as ArrayList<Entry>
        bindAdapter()
    }

    private fun bindAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView?.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = MyItemRecyclerViewAdapter(listPoints)
        recyclerView?.adapter = adapter
    }
}