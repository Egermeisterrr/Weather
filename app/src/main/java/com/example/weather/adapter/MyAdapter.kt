package com.example.weather.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.DayItem

class MyAdapter(
    private val dataset: ArrayList<DayItem>
): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}