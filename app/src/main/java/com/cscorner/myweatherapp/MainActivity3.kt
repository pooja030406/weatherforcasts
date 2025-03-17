package com.cscorner.myweatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cscorner.myweatherapp.databinding.ActivityMain3Binding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var adap: madap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val city = intent.getStringExtra("city")


        val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        var retrofitdata = retrofit.myweatherdata("62d4d34711154eeca2b34902251603",city,7,"no","no")
        retrofitdata.enqueue(object : retrofit2.Callback<weather> {
            override fun onResponse(p0: Call<weather>, p1: Response<weather>) {
                var response = p1.body()
                if (response != null) {
                    binding.rv.layoutManager = LinearLayoutManager(this@MainActivity3, LinearLayoutManager.HORIZONTAL, false)
                    adap= madap(this@MainActivity3, response.forecast.forecastday[0].hour)
                    binding.rv.adapter = adap
                    binding.textView.text = response.location.name.toString()
                    binding.textView2.text = "${response.current.temp_c}°C"
                    binding.textView6.text = "${response.forecast.forecastday[0].day.maxtemp_c}°C"
                    binding.textView7.text = "${response.forecast.forecastday[0].day.mintemp_c}°C"
                    binding.date.text = response.forecast.forecastday[0].date.toString()
                    binding.textView12.text = "${response.forecast.forecastday[0].day.daily_chance_of_rain}%"
                    binding.textView14.text = "${response.current.humidity}%"
                    binding.textView13.text = response.current.wind_kph.toString()+"km/h"


                }
                }


            override fun onFailure(p0: Call<weather>, p1: Throwable) {
               Log.e("TAG", "onFailure: " + p1.message)
            }
    })
        binding.imageView3.setOnClickListener {
           val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            intent.putExtra("city", city)
            startActivity(intent)
        }

    }
}