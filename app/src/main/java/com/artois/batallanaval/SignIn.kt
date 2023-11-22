package com.artois.batallanaval

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {
    lateinit var usuario: EditText
    lateinit var nombre: EditText
    lateinit var password: EditText
    lateinit var signin: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        //variables de la vista
        usuario = findViewById(R.id.usuario)
        nombre = findViewById(R.id.nombre)
        password = findViewById(R.id.Password)
        signin = findViewById(R.id.signin)

        signin.setOnClickListener{
            //guardamos en base el usuario
            database = Firebase.database.reference
            val user = usuario(usuario.text.toString(),nombre.text.toString(), password.text.toString(),0,0,"",0,0)
            database.child("usuarios").child(usuario.text.toString()).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"Usuario agregado correctamente",Toast.LENGTH_LONG).show()
                val intent = Intent(this,seleccion::class.java)
                intent.putExtra("usuario", usuario.text.toString())
                startActivity(intent)
            }
            //guardamos en base el tablero
            var renglon:String="0000000000"
            var tab = Tablero(renglon,renglon,renglon,renglon,renglon,renglon,renglon)
            database.child("tableros").child(usuario.text.toString()).setValue(tab)
        }
    }
}