package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class resibido : AppCompatActivity() {

    lateinit var usuario: String
    lateinit var contra: String

    //variable para la conexion con la base
    private lateinit var mdatabase: DatabaseReference
    var hilo3: Thread = Thread()
    var ban=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resibido)

        //variables desde disponibles
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()

        //variables de  vista
        val oponente: TextView = findViewById(R.id.oponente)
        val acept: Button = findViewById(R.id.acept)
        val recha: Button = findViewById(R.id.rechazar)
        val lin: LinearLayout = findViewById(R.id.linear)
        val lin2: LinearLayout = findViewById(R.id.lineardos)
        //nombre de oponente
        mdatabase = Firebase.database.reference
        mdatabase.child("usuarios").child(contra).get().addOnSuccessListener {
            oponente.text=(it.child("nombre").value.toString())
        }

        //aceptar soli
        acept.setOnClickListener {
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(contra).child("peticion").setValue(usuario).addOnSuccessListener {}
            acept.isClickable = false
            acept.isVisible = false
            recha.isClickable = false
            recha.isVisible = false
            lin.isVisible = false
            lin2.isVisible = true
            //hilo de espera
            hilo3 = Thread(Runnable {
                while(ban==0){
                    Thread.sleep(5000)
                    mdatabase = Firebase.database.reference
                    mdatabase.child("usuarios").child(usuario).get().addOnSuccessListener {
                        if(it.child("estado").value.toString()=="2"){
                            ban=1
                        }
                    }
                }
                mdatabase = Firebase.database.reference
                mdatabase.child("usuarios").child(usuario).get().addOnSuccessListener {
                    val intent9 = Intent(this,Acomodo::class.java)
                    intent9.putExtra("usuario", usuario)
                    intent9.putExtra("contra", contra)
                    intent9.putExtra("nbarcos",it.child("barcos").value.toString())
                    startActivity(intent9)
                }
            })
            hilo3.start()
        }
        //rechazar soli
        recha.setOnClickListener {
            ban=1
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(contra).child("peticion").setValue("no").addOnSuccessListener {}
            //poner en linea y sin peticion
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).child("estado").setValue(1).addOnSuccessListener {}
            mdatabase = Firebase.database.reference
            mdatabase.child("usuarios").child(usuario).child("peticion").setValue("").addOnSuccessListener {}
            //llamar al intent
            val intent = Intent(this,disponibles::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
}