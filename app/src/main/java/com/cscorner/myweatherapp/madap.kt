package com.cscorner.myweatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.myweatherapp.databinding.ViewBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class madap (var context:Context,var list:List<Hour>): RecyclerView.Adapter<madap.ViewHolder>() {
    class ViewHolder(var binding: ViewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): madap.ViewHolder {
        val eachview = ViewBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(eachview)


    }

    override fun onBindViewHolder(holder: madap.ViewHolder, position: Int) {
        fun convertTo12HourFormat(time: String): String {
            val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

            return try {
                val date = inputFormat.parse(time)
                outputFormat.format(date!!)
            } catch (e: Exception) {
                "Invalid Time"
            }
        }
        holder.binding.textView8.text = convertTo12HourFormat(list[position].time.toString().substring(11, 16))
        holder.binding.textView9.text = "${list[position].temp_c}°C"
        Picasso.get().load("https:"+list[position].condition.icon).into(holder.binding.imageView2)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
