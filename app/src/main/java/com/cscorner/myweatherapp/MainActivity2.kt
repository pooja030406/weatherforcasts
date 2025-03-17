package com.cscorner.myweatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cscorner.myweatherapp.databinding.ActivityMain2Binding
import com.cscorner.myweatherapp.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    companion object
    {
        var nav=true
    }
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.button.setOnClickListener {
            val city = binding.editTextText.text.toString()
            if(nav)
            {
                Intent(this, MainActivity3::class.java).also {
                    it.putExtra("city", city)
                    startActivity(it)
                }
            }
            else {
                Intent(this, MainActivity4::class.java).also {
                    it.putExtra("city", city)
                    nav=true
                    startActivity(it)
                }
            }


        }
    }
}