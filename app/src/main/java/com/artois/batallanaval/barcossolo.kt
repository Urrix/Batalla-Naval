package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class barcossolo : AppCompatActivity() {
    var bombs: Int = 0
    //variable para la conexion con la base
    private lateinit var mdatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcossolo)
        val bundle = intent.extras
        var usuario = bundle?.getString("usuario").toString()

        val bomb = resources.getStringArray(R.array.bombas)
        val spinnerb = findViewById<Spinner>(R.id.num_bombas)

        if (spinnerb != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bomb)
            spinnerb.adapter = adapter

            spinnerb.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    bombs=bomb[position].toInt()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val btn: Button = findViewById(R.id.continuar)
        btn.setOnClickListener {
            spinnerb.isClickable = false
            btn.isClickable = false
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).child("bombas").setValue(bombs).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child("root").child("bombas").setValue(bombs).addOnSuccessListener {}
            val intent = Intent(this,acomodandos::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
}