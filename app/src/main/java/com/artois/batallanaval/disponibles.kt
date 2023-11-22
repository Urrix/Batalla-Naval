package com.artois.batallanaval

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class disponibles : AppCompatActivity() {

    //variable para la conexion con la base
    private lateinit var dbase: DatabaseReference
    private lateinit var usuarioss: ArrayList<usuario>
    private lateinit var usuarion: String
    //hilo
    var hilo: Thread = Thread()
    lateinit var contrincante: String
    var bandera=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disponibles)

        //usuario enviado desde intent
        val bundle = intent.extras
        usuarion = bundle?.getString("usuario").toString()

        usuarioss = arrayListOf<usuario>()
        //funcion que obtiene los datos de los usuarios en linea y los muestra en una lista
        getusersData(this)
    }
    init {
        bandera=0
        hilo = Thread(Runnable {
            dbase = Firebase.database.reference
            while(bandera==0){
                Thread.sleep(10000)
                dbase = Firebase.database.reference
                dbase.child("usuarios").child(usuarion).get().addOnSuccessListener {
                    if(it.child("peticion").value!="" && it.child("peticion").value!=null){
                        bandera=1
                        contrincante=it.child("peticion").value.toString()
                    }
                }
            }
            if(bandera==1){
                //llamamos a intent con nueva ventana para conexion
                dbase = Firebase.database.reference
                dbase.child("usuarios").child(usuarion).child("estado").setValue(0).addOnSuccessListener {}
                val intent1 = Intent(this,resibido::class.java)
                intent1.putExtra("usuario", usuarion)
                intent1.putExtra("contra", contrincante)
                startActivity(intent1)
            }
        })
        hilo.start()
    }
    private fun getusersData(mContext: Context){
        dbase = FirebaseDatabase.getInstance().getReference("usuarios")
        dbase.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    usuarioss.clear()
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(usuario::class.java)
                        if (user != null) {
                            if(user.estado==1 && user.user!=usuarion){
                                usuarioss.add(user!!)
                            }
                        }

                    }
                    val adapter = Adaptadorusuarios(mContext,usuarioss)
                    val lista : ListView = findViewById(R.id.lista)
                    lista.adapter = adapter
                    lista.setOnItemClickListener { parent, view, position, id ->
                        bandera=2
                        dbase = Firebase.database.reference
                        dbase.child("usuarios").child(usuarion).child("estado").setValue(0).addOnSuccessListener {}
                        dbase.child("usuarios").child(usuarioss[position].user.toString()).child("peticion").setValue(usuarion).addOnSuccessListener{
                            val intent2 = Intent(mContext,enviado::class.java)
                            intent2.putExtra("usuario", usuarion)
                            intent2.putExtra("contra", usuarioss[position].user)
                            startActivity(intent2)
                        }
                        /*val intent = Intent(this,ProductoActivity::class.java)
                            intent.putExtra("producto", listaproductos[position])
                            startActivity(intent)*/
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}