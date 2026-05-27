package com.example.pratica10_pdm

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Atividade3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.atividade3)

        val campo_user = findViewById<EditText>(R.id.usuario)
        val chave = findViewById<Switch>(R.id.lembrar)
        val entrar = findViewById<Button>(R.id.botao_entrar)

        val dados = getSharedPreferences("meus_dados", Context.MODE_PRIVATE)
        
        val esta_marcado = dados.getBoolean("chave_lembrar", false)
        chave.isChecked = esta_marcado

        if (esta_marcado) {
            campo_user.setText(dados.getString("nome_usuario", ""))
        }

        entrar.setOnClickListener {
            if (chave.isChecked) {
                dados.edit().putString("nome_usuario", campo_user.text.toString()).apply()
                dados.edit().putBoolean("chave_lembrar", true).apply()
            } else {
                dados.edit().putString("nome_usuario", "").apply()
                dados.edit().putBoolean("chave_lembrar", false).apply()
            }
            Toast.makeText(this, "login feito", Toast.LENGTH_SHORT).show()
        }
    }
}