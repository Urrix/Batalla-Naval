package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class seleccion : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion)

        //usuario enviado desde intent
        val bundle = intent.extras
        var usuario = bundle?.getString("usuario")

        //variables de vitsa
        val solo: ImageButton = findViewById(R.id.solo)
        val multi: ImageButton = findViewById(R.id.multijugador)

        solo.setOnClickListener{
            Toast.makeText(this,"Al jugar en solitario no aumentaras victorias!!",Toast.LENGTH_LONG).show()
            val intent = Intent(this,barcossolo::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
        multi.setOnClickListener{
            //cambiar el estado a online
            database = Firebase.database.reference
            if (usuario != null) {
                database.child("usuarios").child(usuario).child("estado").setValue(1).addOnSuccessListener {}
            }
            //cambiar a ventana de disponibilidad
            val intent = Intent(this,disponibles::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
}