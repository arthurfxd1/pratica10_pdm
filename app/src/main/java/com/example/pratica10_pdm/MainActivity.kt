package com.example.pratica10_pdm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pratica10_pdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, Atividade1::class.java))
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this, Atividade2::class.java))
        }

        binding.btn3.setOnClickListener {
            startActivity(Intent(this, Atividade3::class.java))
        }
    }
}
