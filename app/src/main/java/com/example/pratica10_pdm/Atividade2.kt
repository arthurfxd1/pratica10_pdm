package com.example.pratica10_pdm

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Atividade2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.atividade2)

        val mostrar = findViewById<TextView>(R.id.texto_contador)
        val botao = findViewById<Button>(R.id.botao_clique)

        val dados = getSharedPreferences("meus_dados", Context.MODE_PRIVATE)
        
        var numero = dados.getInt("contador", 0)
        mostrar.text = numero.toString()

        botao.setOnClickListener {
            numero++
            mostrar.text = numero.toString()
            dados.edit().putInt("contador", numero).apply()
        }
    }
}