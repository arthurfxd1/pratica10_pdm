package com.example.pratica10_pdm

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Atividade1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.atividade1)

        val campo = findViewById<EditText>(R.id.campo_texto)
        val adicionar = findViewById<Button>(R.id.botao_adicionar)
        val limpar = findViewById<Button>(R.id.botao_limpar)
        val resultado = findViewById<TextView>(R.id.texto_salvo)

        val dados = getSharedPreferences("meus_dados", Context.MODE_PRIVATE)
        
        resultado.text = dados.getString("anotacao", "")
        campo.setText(dados.getString("texto_atual", ""))

        adicionar.setOnClickListener {
            val novo = campo.text.toString()
            val antigo = resultado.text.toString()
            if (antigo.isEmpty()) {
                resultado.text = novo
            } else {
                resultado.text = antigo + "\n" + novo
            }
            campo.setText("")
        }

        limpar.setOnClickListener {
            resultado.text = ""
            dados.edit().putString("anotacao", "").apply()
        }
    }

    override fun onPause() {
        super.onPause()
        val campo = findViewById<EditText>(R.id.campo_texto)
        val dados = getSharedPreferences("meus_dados", Context.MODE_PRIVATE)
        dados.edit().putString("texto_atual", campo.text.toString()).apply()
    }

    override fun onBackPressed() {
        val campo = findViewById<EditText>(R.id.campo_texto)
        val resultado = findViewById<TextView>(R.id.texto_salvo)
        val dados = getSharedPreferences("meus_dados", Context.MODE_PRIVATE)

        if (campo.text.isNotEmpty()) {
            AlertDialog.Builder(this)
                .setMessage("deseja salvar o texto?")
                .setPositiveButton("sim") { _, _ ->
                    val novo = campo.text.toString()
                    val antigo = resultado.text.toString()
                    val final = if (antigo.isEmpty()) novo else antigo + "\n" + novo
                    dados.edit().putString("anotacao", final).apply()
                    dados.edit().putString("texto_atual", "").apply()
                    finish()
                }
                .setNegativeButton("nao") { _, _ -> 
                    dados.edit().putString("anotacao", resultado.text.toString()).apply()
                    dados.edit().putString("texto_atual", "").apply()
                    finish() 
                }
                .show()
        } else {
            dados.edit().putString("anotacao", resultado.text.toString()).apply()
            super.onBackPressed()
        }
    }
}