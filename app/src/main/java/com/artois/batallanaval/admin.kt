package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class admin : AppCompatActivity() {

    //variable para la conexion con la base
    private lateinit var mdatabase: DatabaseReference

    lateinit var usuario: String
    lateinit var contra: String
    var bombs: Int = 0
    var barcs: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        //variables por intent
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()

        //primer spiner
        // access the spinner
        val barc = resources.getStringArray(R.array.barcos)
        val bomb = resources.getStringArray(R.array.bombas)
        val spinner = findViewById<Spinner>(R.id.num_barcos)
        val spinnerb = findViewById<Spinner>(R.id.num_bombas)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, barc)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    barcs=barc[position].toInt()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        //segundo spiner
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

        //boton
        val btn: Button = findViewById(R.id.continuar)
        btn.setOnClickListener {
            spinner.isClickable = false
            spinnerb.isClickable = false
            btn.isClickable = false
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).child("barcos").setValue(barcs).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).child("bombas").setValue(bombs).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(contra).child("barcos").setValue(barcs).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(contra).child("bombas").setValue(bombs).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(contra).child("estado").setValue(2).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).get().addOnSuccessListener {
                val intent9 = Intent(this,Acomodo::class.java)
                intent9.putExtra("usuario", usuario)
                intent9.putExtra("contra", contra)
                intent9.putExtra("nbarcos",it.child("barcos").value.toString())
                startActivity(intent9)
            }

        }
    }
}