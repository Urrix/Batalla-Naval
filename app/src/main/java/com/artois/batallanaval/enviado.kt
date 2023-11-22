package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_enviado.*

class enviado : AppCompatActivity() {

    //variable para la conexion con la base
    private lateinit var mdatabase: DatabaseReference

    lateinit var tiempo: TextView
    var hilo2: Thread = Thread()
    var band=0
    lateinit var usuario: String
    lateinit var contra: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviado)

        //variables por intent
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()
        //variables de vista
        val uno: TextView = findViewById(R.id.uno)
        val dos: TextView = findViewById(R.id.dos)
        tiempo  = findViewById(R.id.time)
        val cancel: Button = findViewById(R.id.cancel)

        //conexion a base para traer los nombres de los usuarios
        mdatabase = Firebase.database.reference
        mdatabase.child("usuarios").child(usuario).get().addOnSuccessListener {
            uno.text = it.child("nombre").value.toString()
        }
        mdatabase.child("usuarios").child(contra).get().addOnSuccessListener {
            dos.text=(it.child("nombre").value.toString())
        }
        cancel.setOnClickListener {
            //quitar la peticion
            mdatabase.child("usuarios").child(contra).child("peticion").setValue("").addOnSuccessListener {}
            mdatabase.child("usuarios").child(usuario).child("estado").setValue(1).addOnSuccessListener {}
            //llamar al intent
            val intent = Intent(this,disponibles::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
    fun poner(){
        if(band==1){
            tiempo.text = "Solicitud Rechazada!!"
        }
        if(band==2){
            tiempo.text = "Solicitud Aceptada!!"
        }
        cancel.isVisible = false
        cancel.isClickable = false
    }
    init{
        hilo2 = Thread(Runnable {
            mdatabase = Firebase.database.reference
            while(band==0){
                Thread.sleep(5000)
                mdatabase = Firebase.database.reference
                mdatabase.child("usuarios").child(usuario).get().addOnSuccessListener {
                    if(it.child("peticion").value!=""){
                        if(it.child("peticion").value=="no"){
                            band=1
                            mdatabase = Firebase.database.reference
                            mdatabase.child("usuarios").child(usuario).child("peticion").setValue("").addOnSuccessListener {  }
                            mdatabase = Firebase.database.reference
                            mdatabase.child("usuarios").child(usuario).child("estado").setValue(1).addOnSuccessListener {  }
                            poner()
                            Thread.sleep(5000)
                            val intent1 = Intent(this,disponibles::class.java)
                            intent1.putExtra("usuario", usuario)
                            startActivity(intent1)
                        }else{
                            if (it.child("peticion").value==contra){
                                band=2
                                poner()
                                Thread.sleep(5000)
                                //Intent a Seleccion de barcos
                                mdatabase = Firebase.database.reference
                                mdatabase.child("usuarios").child(usuario).child("turno").setValue("1").addOnSuccessListener {  }
                                mdatabase = Firebase.database.reference
                                mdatabase.child("usuarios").child(contra).child("turno").setValue("").addOnSuccessListener {  }
                                val intent2 = Intent(this,admin::class.java)
                                intent2.putExtra("usuario", usuario)
                                intent2.putExtra("contra", contra)
                                startActivity(intent2)
                            }
                        }
                    }
                }
            }
        })
        hilo2.start()
    }
}