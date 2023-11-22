package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class resultadosolo : AppCompatActivity() {
    lateinit var datos: DatabaseReference
    lateinit var usuario: String
    lateinit var texto: String
    lateinit var text: TextView
    lateinit var boton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultadosolo)

        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        texto = bundle?.getString("mensaje").toString()

        text = findViewById(R.id.texto)
        boton = findViewById(R.id.continuar)

        text.text=texto

        boton.setOnClickListener{
            datos = Firebase.database.reference
            datos.child("usuarios").child(usuario).child("turno").setValue("").addOnSuccessListener {}
            //intent a home
            val intent = Intent(this,seleccion::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
}