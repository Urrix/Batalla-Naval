package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //variables de vista
    lateinit var usuario:EditText
    lateinit var password:EditText
    lateinit var login:Button
    lateinit var signin:Button
    //variable para la conexion con la base
    private lateinit var mdatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        //variables de vista
        usuario = findViewById(R.id.usuario)
        password = findViewById(R.id.Password)
        login = findViewById(R.id.login)
        signin = findViewById(R.id.signin)

        signin.setOnClickListener{
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        login.setOnClickListener{
            //si los datos no estan vacios
            if(usuario.text.toString()!=""&&password.text.toString()!=""){
                //consultar en base de datos
                mdatabase = Firebase.database.reference
                mdatabase.child("usuarios").child(usuario.text.toString()).get().addOnSuccessListener {
                    if(it.exists()){
                        if(it.child("password").value==password.text.toString()){
                            val intent = Intent(this,seleccion::class.java)
                            intent.putExtra("usuario", usuario.text.toString())
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"contraseña incorrecta",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this,"Usuario no encontrado",Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }else{
                Toast.makeText(this,"Debes ingresar todos los datos para hacer Log In", Toast.LENGTH_LONG).show()
            }
        }
    }
}