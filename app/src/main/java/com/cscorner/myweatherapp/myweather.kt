package com.cscorner.myweatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.myweatherapp.databinding.EachviewBinding
import com.squareup.picasso.Picasso
import java.util.Calendar

class myweather(var context: Context, var list:List<Forecastday>): RecyclerView.Adapter<myweather.ViewHolder>() {
    class ViewHolder(var binding: EachviewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val oneview = EachviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(oneview)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        fun getDayOfWeek(dateStr: String): String {
            val parts = dateStr.split("-") // Splitting "YYYY-MM-DD"

            if (parts.size != 3) return "Invalid Date"

            val year = parts[0].toInt()
            val month = parts[1].toInt() - 1
            val day = parts[2].toInt()
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val days = arrayOf("Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat")
            val dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1
            return days[dayIndex]
        }
        holder.binding.days.text = getDayOfWeek(list[position].date)
        Picasso.get().load("https:"+list[position].day.condition.icon).into(holder.binding.image)
        holder.binding.max.text = list[position].day.maxtemp_c.toString()+"°C"
        holder.binding.min.text = list[position].day.mintemp_c.toString()+"°C"


    }

}
