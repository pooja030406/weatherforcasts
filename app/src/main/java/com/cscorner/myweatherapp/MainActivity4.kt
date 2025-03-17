package com.cscorner.myweatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cscorner.myweatherapp.MainActivity2.Companion.nav
import com.cscorner.myweatherapp.databinding.ActivityMain4Binding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    private lateinit var adapnew: myweather
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val city = intent.getStringExtra("city")


        val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(
            GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        var retrofitdata = retrofit.myweatherdata("62d4d34711154eeca2b34902251603",city,7,"no","no")
        retrofitdata.enqueue(object : retrofit2.Callback<weather> {
            override fun onResponse(p0: Call<weather>, p1: Response<weather>) {
                var response = p1.body()
                if (response != null) {
                    binding.recycleview.layoutManager = LinearLayoutManager(this@MainActivity4, LinearLayoutManager.VERTICAL, false)
                    adapnew= myweather(this@MainActivity4, response.forecast.forecastday)
                    binding.recycleview.adapter = adapnew
                    binding.textView.text = response.location.name.toString()
                    binding.textView2.text = "${response.current.temp_c}°C"
                    binding.textView6.text = "${response.forecast.forecastday[0].day.maxtemp_c}°C"
                    binding.textView7.text = "${response.forecast.forecastday[0].day.mintemp_c}°C"
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
            nav=false
            startActivity(intent)

        }
        binding.button2.setOnClickListener {
           val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}