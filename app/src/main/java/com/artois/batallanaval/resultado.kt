package com.artois.batallanaval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class resultado : AppCompatActivity() {
    lateinit var datos: DatabaseReference
    lateinit var usuario: String
    lateinit var contra: String
    lateinit var texto: String
    lateinit var caso: String
    lateinit var text: TextView
    lateinit var boton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()
        texto = bundle?.getString("mensaje").toString()
        caso = bundle?.getString("caso").toString()
        var ganes="0"
        var gan=0
        text = findViewById(R.id.texto)
        boton = findViewById(R.id.continuar)

        text.text=texto

        boton.setOnClickListener{
            if(caso=="1") {
                datos = FirebaseDatabase.getInstance().getReference("usuarios")
                datos.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            ganes=snapshot.child(contra).child("victorias").value.toString()
                            gan = ganes.toInt()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
                //poner normalidad
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("victorias").setValue(gan++).addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("gane").setValue(0).addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("gane").setValue(0).addOnSuccessListener {}
            }else
            if (caso=="2"){
                //poner gane a usuario
                datos = FirebaseDatabase.getInstance().getReference("usuarios")
                datos.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            ganes=snapshot.child(usuario).child("victorias").value.toString()
                            gan = ganes.toInt()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
                //poner normalidad
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("victorias").setValue(gan++).addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("gane").setValue(0).addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("gane").setValue(0).addOnSuccessListener {}
            }else{
                //poner normalidad
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(usuario).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("peticion").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
                datos.child("usuarios").child(contra).child("turno").setValue("").addOnSuccessListener {}
                datos = Firebase.database.reference
            }
            //intent a home
            val intent = Intent(this,seleccion::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }
}