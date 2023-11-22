package com.artois.batallanaval

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.isVisible
import com.artois.batallanaval.databinding.ActivityJuegoBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_juego.*

class Juego : AppCompatActivity() {
    lateinit var usuario: String
    lateinit var contra: String
    //binding
    private lateinit var binding: ActivityJuegoBinding
    //variable para la conexion con la base
    lateinit var datos: DatabaseReference
    var turno = ""
    var renglonn = ""
    var renglon = arrayOf("","","","","","","","","","")
    var renglon2 = arrayOf("","","","","","","")
    var matrizu=Array(7){IntArray(10)}
    var matrizc=Array(7){IntArray(10)}
    var numerob = "1"
    var numbomb= "0"
    var bombs=1
    var hilo: Thread = Thread()
    //banderas para barcos
    var bandera=0
    var barco1 = 0
    var barco2 = 0
    var barco3 = 0
    var barco4 = 0
    var barco5 = 0
    var gane = "0"
    var estadoc = ""
    var estadou = ""
    var array = arrayOf(0,0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_juego)
        binding = ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //variables de intent
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()

        binding.play?.setOnClickListener {
            datos = FirebaseDatabase.getInstance().getReference("usuarios")
            datos.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        estadou=snapshot.child(usuario).child("estado").value.toString()
                        turno=snapshot.child(usuario).child("turno").value.toString()
                        numerob=snapshot.child(usuario).child("barcos").value.toString()
                        numbomb=snapshot.child(usuario).child("bombas").value.toString()
                        estadoc=snapshot.child(contra).child("estado").value.toString()
                        gane=snapshot.child(contra).child("gane").value.toString()
                        bombs=numbomb.toInt()
                        binding.bombas?.text=numbomb
                        if(turno=="1"){
                            binding.estatus?.setBackgroundColor(Color.rgb(15,229,141))
                        }else{
                            binding.estatus?.setBackgroundColor(Color.rgb(229,25,93))
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            datos = Firebase.database.reference
            datos.child("usuarios").child(usuario).child("gane").setValue(0).addOnSuccessListener {  }
            if(estadou=="3" && estadoc=="3"){
                recuperarbc(this)
                gettababajo(this)
                binding.play?.isClickable = false
                binding.play?.isVisible = false
                binding.tableros?.isVisible = true
                jugar()
                hilo = Thread(Runnable {
                    var x=0
                    var y=0
                    var suma=0
                    //si las bombas se acaban se acaba el juego
                    while (bombs!=0 && bandera<numerob.toInt() && gane=="0"){
                        x=0
                        suma=0
                        while(x<7){
                            y=0
                            while(y<10){
                                if(matrizc[x][y]==7){
                                    suma++
                                }
                                y++
                            }
                            x++
                        }
                        Log.e("yoo:", suma.toString())
                            if(suma==1){
                                bandera=1
                            }
                            if(suma==3){
                                bandera=2
                            }
                            if(suma==6){
                                bandera=3
                            }
                            if(suma==10){
                                bandera=4
                            }
                            if(suma==15){
                                bandera=5
                            }
                        Thread.sleep(1000)
                    }
                    if(gane=="1"){
                        //gano el contra
                        val intent9 = Intent(this,resultado::class.java)
                        intent9.putExtra("usuario", usuario)
                        intent9.putExtra("contra", contra)
                        intent9.putExtra("mensaje", "perdiste!!")
                        intent9.putExtra("caso", "1")
                        startActivity(intent9)
                    }else
                        if(bandera==numerob.toInt()){
                            //gano usuario
                            datos = Firebase.database.reference
                            datos.child("usuarios").child(usuario).child("gane").setValue(1).addOnSuccessListener {  }
                            val intent10 = Intent(this,resultado::class.java)
                            intent10.putExtra("usuario", usuario)
                            intent10.putExtra("contra", contra)
                            intent10.putExtra("mensaje", "ganaste!!")
                            intent10.putExtra("caso", "2")
                            startActivity(intent10)
                        }else {
                            //empate
                            val intent11 = Intent(this,resultado::class.java)
                            intent11.putExtra("usuario", usuario)
                            intent11.putExtra("contra", contra)
                            intent11.putExtra("mensaje", "empate!!")
                            intent11.putExtra("caso", "3")
                            startActivity(intent11)
                        }
                })
                hilo.start()
            }
        }
    }
    private fun recuperarbc(mContext: Context){
        datos = FirebaseDatabase.getInstance().getReference("tableros").child(contra)
        datos.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var j=0
                    renglonn=snapshot.child("uno").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[0][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("dos").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[1][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("tres").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[2][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("cuatro").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[3][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("cinco").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[4][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("seis").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[5][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("siete").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizc[6][j]=renglon[j].toInt()
                        j++
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun pintarabajo(){
        var i=0
        var j=0
        var h=1
        while (i<7){
            j=0
            while (j<10){
                if(matrizu[i][j]==1){
                    imagen(h)?.setBackgroundResource(R.drawable.lancha)
                }else
                    if(matrizu[i][j]==2){
                        imagen(h)?.setBackgroundResource(R.drawable.crucero)
                    }else
                        if(matrizu[i][j]==3){
                            imagen(h)?.setBackgroundResource(R.drawable.subma)
                        }else
                            if(matrizu[i][j]==4){
                                imagen(h)?.setBackgroundResource(R.drawable.buque)
                            }else
                                if(matrizu[i][j]==5){
                                    imagen(h)?.setBackgroundResource(R.drawable.aviones)
                                }else
                                    if(matrizu[i][j]==6){
                                        imagen(h)?.setBackgroundResource(R.drawable.agua)
                                    }else
                                        if(matrizu[i][j]==7){
                                            imagen(h)?.setBackgroundResource(R.drawable.fuego)
                                        }
            h++
                j++
            }
            i++
        }
    }
    fun imagen(i: Int): ImageView {
        if (i == 1) {
            return binding.pp1
        }else
        if (i == 2) {
            return binding.pp2
        }else
        if(i==3){
            return binding.pp3
        }else
        if(i==4){
            return binding.pp4
        }else
        if(i==5){
            return binding.pp5
        }else
        if(i==6){
            return binding.pp6
        }else
        if(i==7){
            return binding.pp7
        }else
        if(i==8){
            return binding.pp8
        }else
        if(i==9){
            return binding.pp9
        }else
        if(i==10){
            return binding.pp10
        }else
            if (i == 11) {
                return binding.pp11
            }else
                if (i == 12) {
                    return binding.pp12
                }else
                    if(i==13){
                        return binding.pp13
                    }else
                        if(i==14){
                            return binding.pp14
                        }else
                            if(i==15){
                                return binding.pp15
                            }else
                                if(i==16){
                                    return binding.pp16
                                }else
                                    if(i==17){
                                        return binding.pp17
                                    }else
                                        if(i==18){
                                            return binding.pp18
                                        }else
                                            if(i==19){
                                                return binding.pp19
                                            }else
                                                if(i==20){
                                                    return binding.pp20
                                                }else
                                                    if (i == 21) {
                                                        return binding.pp21
                                                    }else
                                                        if (i == 22) {
                                                            return binding.pp22
                                                        }else
                                                            if(i==23){
                                                                return binding.pp23
                                                            }else
                                                                if(i==24){
                                                                    return binding.pp24
                                                                }else
                                                                    if(i==25){
                                                                        return binding.pp25
                                                                    }else
                                                                        if(i==26){
                                                                            return binding.pp26
                                                                        }else
                                                                            if(i==27){
                                                                                return binding.pp27
                                                                            }else
                                                                                if(i==28){
                                                                                    return binding.pp28
                                                                                }else
                                                                                    if(i==29){
                                                                                        return binding.pp29
                                                                                    }else
                                                                                        if(i==30){
                                                                                            return binding.pp30
                                                                                        }else
                                                                                            if (i == 31) {
                                                                                                return binding.pp31
                                                                                            }else
                                                                                                if (i == 32) {
                                                                                                    return binding.pp32
                                                                                                }else
                                                                                                    if(i==33){
                                                                                                        return binding.pp33
                                                                                                    }else
                                                                                                        if(i==34){
                                                                                                            return binding.pp34
                                                                                                        }else
                                                                                                            if(i==35){
                                                                                                                return binding.pp35
                                                                                                            }else
                                                                                                                if(i==36){
                                                                                                                    return binding.pp36
                                                                                                                }else
                                                                                                                    if(i==37){
                                                                                                                        return binding.pp37
                                                                                                                    }else
                                                                                                                        if(i==38){
                                                                                                                            return binding.pp38
                                                                                                                        }else
                                                                                                                            if(i==39){
                                                                                                                                return binding.pp39
                                                                                                                            }else
                                                                                                                                if(i==40){
                                                                                                                                    return binding.pp40
                                                                                                                                }else
                                                                                                                                    if (i == 41) {
                                                                                                                                        return binding.pp41
                                                                                                                                    }else
                                                                                                                                        if (i == 42) {
                                                                                                                                            return binding.pp42
                                                                                                                                        }else
                                                                                                                                            if(i==43){
                                                                                                                                                return binding.pp43
                                                                                                                                            }else
                                                                                                                                                if(i==44){
                                                                                                                                                    return binding.pp44
                                                                                                                                                }else
                                                                                                                                                    if(i==45){
                                                                                                                                                        return binding.pp45
                                                                                                                                                    }else
                                                                                                                                                        if(i==46){
                                                                                                                                                            return binding.pp46
                                                                                                                                                        }else
                                                                                                                                                            if(i==47){
                                                                                                                                                                return binding.pp47
                                                                                                                                                            }else
                                                                                                                                                                if(i==48){
                                                                                                                                                                    return binding.pp48
                                                                                                                                                                }else
                                                                                                                                                                    if(i==49){
                                                                                                                                                                        return binding.pp49
                                                                                                                                                                    }else
                                                                                                                                                                        if(i==50){
                                                                                                                                                                            return binding.pp50
                                                                                                                                                                        }else
                                                                                                                                                                            if (i == 51) {
                                                                                                                                                                                return binding.pp51
                                                                                                                                                                            }else
                                                                                                                                                                                if (i == 52) {
                                                                                                                                                                                    return binding.pp52
                                                                                                                                                                                }else
                                                                                                                                                                                    if(i==53){
                                                                                                                                                                                        return binding.pp53
                                                                                                                                                                                    }else
                                                                                                                                                                                        if(i==54){
                                                                                                                                                                                            return binding.pp54
                                                                                                                                                                                        }else
                                                                                                                                                                                            if(i==55){
                                                                                                                                                                                                return binding.pp55
                                                                                                                                                                                            }else
                                                                                                                                                                                                if(i==56){
                                                                                                                                                                                                    return binding.pp56
                                                                                                                                                                                                }else
                                                                                                                                                                                                    if(i==57){
                                                                                                                                                                                                        return binding.pp57
                                                                                                                                                                                                    }else
                                                                                                                                                                                                        if(i==58){
                                                                                                                                                                                                            return binding.pp58
                                                                                                                                                                                                        }else
                                                                                                                                                                                                            if(i==59){
                                                                                                                                                                                                                return binding.pp59
                                                                                                                                                                                                            }else
                                                                                                                                                                                                                if(i==60){
                                                                                                                                                                                                                    return binding.pp60
                                                                                                                                                                                                                }else
                                                                                                                                                                                                                    if (i == 61) {
                                                                                                                                                                                                                        return binding.pp61
                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                        if (i == 62) {
                                                                                                                                                                                                                            return binding.pp62
                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                            if(i==63){
                                                                                                                                                                                                                                return binding.pp63
                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                if(i==64){
                                                                                                                                                                                                                                    return binding.pp64
                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                    if(i==65){
                                                                                                                                                                                                                                        return binding.pp65
                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                        if(i==66){
                                                                                                                                                                                                                                            return binding.pp66
                                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                                            if(i==67){
                                                                                                                                                                                                                                                return binding.pp67
                                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                                if(i==68){
                                                                                                                                                                                                                                                    return binding.pp68
                                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                                    if(i==69){
                                                                                                                                                                                                                                                        return binding.pp69
                                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                                        if(i==70){
                                                                                                                                                                                                                                                            return binding.pp70
                                                                                                                                                                                                                                                        }
    return binding.pp1
    }
    fun boton(i: Int): ImageButton{
        if(i==1){
            return binding.p1
        }else
            if(i==2){
                return binding.p2
            }else
                if(i==3){
                    return binding.p3
                }else
                    if(i==4){
                        return binding.p4
                    }else
                        if(i==5){
                            return binding.p5
                        }else
                            if(i==6){
                                return binding.p6
                            }else
                                if(i==7){
                                    return binding.p7
                                }else
                                    if(i==8){
                                        return binding.p8
                                    }else
                                        if(i==9){
                                            return binding.p9
                                        }else
                                            if(i==10){
                                                return binding.p10
                                            }else
                                                if(i==11){
                                                    return binding.p11
                                                }else
                                                    if(i==12){
                                                        return binding.p12
                                                    }else
                                                        if(i==13){
                                                            return binding.p13
                                                        }else
                                                            if(i==14){
                                                                return binding.p14
                                                            }else
                                                                if(i==15){
                                                                    return binding.p15
                                                                }else
                                                                    if(i==16){
                                                                        return binding.p16
                                                                    }else
                                                                        if(i==17){
                                                                            return binding.p17
                                                                        }else
                                                                            if(i==18){
                                                                                return binding.p18
                                                                            }else
                                                                                if(i==19){
                                                                                    return binding.p19
                                                                                }else
                                                                                    if(i==20){
                                                                                        return binding.p20
                                                                                    }else
                                                                                        if(i==21){
                                                                                            return binding.p21
                                                                                        }else
                                                                                            if(i==22){
                                                                                                return binding.p22
                                                                                            }else
                                                                                                if(i==23){
                                                                                                    return binding.p23
                                                                                                }else
                                                                                                    if(i==24){
                                                                                                        return binding.p24
                                                                                                    }else
                                                                                                        if(i==25){
                                                                                                            return binding.p25
                                                                                                        }else
                                                                                                            if(i==26){
                                                                                                                return binding.p26
                                                                                                            }else
                                                                                                                if(i==27){
                                                                                                                    return binding.p27
                                                                                                                }else
                                                                                                                    if(i==28){
                                                                                                                        return binding.p28
                                                                                                                    }else
                                                                                                                        if(i==29){
                                                                                                                            return binding.p29
                                                                                                                        }else
                                                                                                                            if(i==30){
                                                                                                                                return binding.p30
                                                                                                                            }else
                                                                                                                                if(i==31){
                                                                                                                                    return binding.p31
                                                                                                                                }else
                                                                                                                                    if(i==32){
                                                                                                                                        return binding.p32
                                                                                                                                    }else
                                                                                                                                        if(i==33){
                                                                                                                                            return binding.p33
                                                                                                                                        }else
                                                                                                                                            if(i==34){
                                                                                                                                                return binding.p34
                                                                                                                                            }else
                                                                                                                                                if(i==35){
                                                                                                                                                    return binding.p35
                                                                                                                                                }else
                                                                                                                                                    if(i==36){
                                                                                                                                                        return binding.p36
                                                                                                                                                    }else
                                                                                                                                                        if(i==37){
                                                                                                                                                            return binding.p37
                                                                                                                                                        }else
                                                                                                                                                            if(i==38){
                                                                                                                                                                return binding.p38
                                                                                                                                                            }else
                                                                                                                                                                if(i==39){
                                                                                                                                                                    return binding.p39
                                                                                                                                                                }else
                                                                                                                                                                    if(i==40){
                                                                                                                                                                        return binding.p40
                                                                                                                                                                    }else
                                                                                                                                                                        if(i==41){
                                                                                                                                                                            return binding.p41
                                                                                                                                                                        }else
                                                                                                                                                                            if(i==42){
                                                                                                                                                                                return binding.p42
                                                                                                                                                                            }else
                                                                                                                                                                                if(i==43){
                                                                                                                                                                                    return binding.p43
                                                                                                                                                                                }else
                                                                                                                                                                                    if(i==44){
                                                                                                                                                                                        return binding.p44
                                                                                                                                                                                    }else
                                                                                                                                                                                        if(i==45){
                                                                                                                                                                                            return binding.p45
                                                                                                                                                                                        }else
                                                                                                                                                                                            if(i==46){
                                                                                                                                                                                                return binding.p46
                                                                                                                                                                                            }else
                                                                                                                                                                                                if(i==47){
                                                                                                                                                                                                    return binding.p47
                                                                                                                                                                                                }else
                                                                                                                                                                                                    if(i==48){
                                                                                                                                                                                                        return binding.p48
                                                                                                                                                                                                    }else
                                                                                                                                                                                                        if(i==49){
                                                                                                                                                                                                            return binding.p49
                                                                                                                                                                                                        }else
                                                                                                                                                                                                            if(i==50){
                                                                                                                                                                                                                return binding.p50
                                                                                                                                                                                                            }else
                                                                                                                                                                                                                if(i==51){
                                                                                                                                                                                                                    return binding.p51
                                                                                                                                                                                                                }else
                                                                                                                                                                                                                    if(i==52){
                                                                                                                                                                                                                        return binding.p52
                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                        if(i==53){
                                                                                                                                                                                                                            return binding.p53
                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                            if(i==54){
                                                                                                                                                                                                                                return binding.p54
                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                if(i==55){
                                                                                                                                                                                                                                    return binding.p55
                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                    if(i==56){
                                                                                                                                                                                                                                        return binding.p56
                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                        if(i==57){
                                                                                                                                                                                                                                            return binding.p57
                                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                                            if(i==58){
                                                                                                                                                                                                                                                return binding.p58
                                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                                if(i==59){
                                                                                                                                                                                                                                                    return binding.p59
                                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                                    if(i==60){
                                                                                                                                                                                                                                                        return binding.p60
                                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                                        if(i==61){
                                                                                                                                                                                                                                                            return binding.p61
                                                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                                                            if(i==62){
                                                                                                                                                                                                                                                                return binding.p62
                                                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                                                if(i==63){
                                                                                                                                                                                                                                                                    return binding.p63
                                                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                                                    if(i==64){
                                                                                                                                                                                                                                                                        return binding.p64
                                                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                                                        if(i==65){
                                                                                                                                                                                                                                                                            return binding.p65
                                                                                                                                                                                                                                                                        }else
                                                                                                                                                                                                                                                                            if(i==66){
                                                                                                                                                                                                                                                                                return binding.p66
                                                                                                                                                                                                                                                                            }else
                                                                                                                                                                                                                                                                                if(i==67){
                                                                                                                                                                                                                                                                                    return binding.p67
                                                                                                                                                                                                                                                                                }else
                                                                                                                                                                                                                                                                                    if(i==68){
                                                                                                                                                                                                                                                                                        return binding.p68
                                                                                                                                                                                                                                                                                    }else
                                                                                                                                                                                                                                                                                        if(i==69){
                                                                                                                                                                                                                                                                                            return binding.p69
                                                                                                                                                                                                                                                                                        }else{
                                                                                                                                                                                                                                                                                                return binding.p70
                                                                                                                                                                                                                                                                                            }
    }
    private fun gettababajo(mContext: Context){
        datos = FirebaseDatabase.getInstance().getReference("tableros").child(usuario)
        datos.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var j=0
                    renglonn=""
                    renglonn=snapshot.child("uno").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[0][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("dos").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[1][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("tres").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[2][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("cuatro").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[3][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("cinco").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[4][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("seis").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[5][j]=renglon[j].toInt()
                        j++
                    }
                    renglonn=snapshot.child("siete").value.toString()
                    for (i in renglonn.indices){
                        renglon[i]=renglonn[i].toString()
                    }
                    j=0
                    while (j<10){
                        matrizu[6][j]=renglon[j].toInt()
                        j++
                    }
                    pintarabajo()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun actualizarenem(){
        //limpiar renbglon2
        var h=0
        while (h<7){
            renglon2[h]=""
            h++
        }
        var i=0
        var j=0
        while(i<7){
            j=0
            while(j<10){
                renglon2[i]=renglon2[i]+matrizc[i][j].toString()
                j++
            }
            i++
        }
        var tab = Tablero(renglon2[0],renglon2[1],renglon2[2],renglon2[3],renglon2[4],renglon2[5],renglon2[6])
        datos = Firebase.database.reference
        datos.child("tableros").child(contra).setValue(tab).addOnSuccessListener {  }
    }
    fun jugar(){
        var i=0
        var j=0
        var h=1
        while (i<=6){
            j=0
            while (j<=9){
                boton(h)?.setOnClickListener{
                    posi(it.id)
                    if(turno=="1") {
                        if (matrizc[array[0]][array[1]] == 1) {
                            //7 fuego contra
                            matrizc[array[0]][array[1]] = 7
                            actualizarenem()
                            //pintar que se acerto
                            it.setBackgroundResource(R.drawable.fuego)
                            //turno otra vez
                            barco1++
                        } else
                            if (matrizc[array[0]][array[1]] == 2) {
                                //7 fuego contra
                                matrizc[array[0]][array[1]] = 7
                                actualizarenem()
                                //pintar que se acerto
                                it.setBackgroundResource(R.drawable.fuego)
                                //turno otra vez
                                barco2++
                            } else
                                if (matrizc[array[0]][array[1]] == 3) {
                                    //7 fuego contra
                                    matrizc[array[0]][array[1]] = 7
                                    actualizarenem()
                                    //pintar que se acerto
                                    it.setBackgroundResource(R.drawable.fuego)
                                    //turno otra vez
                                    barco3++
                                } else
                                    if (matrizc[array[0]][array[1]] == 4) {
                                        //7 fuego contra
                                        matrizc[array[0]][array[1]] = 7
                                        actualizarenem()
                                        //pintar que se acerto
                                        it.setBackgroundResource(R.drawable.fuego)
                                        //turno otra vez
                                        barco4++
                                    } else
                                        if (matrizc[array[0]][array[1]] == 5) {
                                            //7 fuego contra
                                            matrizc[array[0]][array[1]] = 7
                                            actualizarenem()
                                            //pintar que se acerto
                                            it.setBackgroundResource(R.drawable.fuego)
                                            //turno otra vez
                                            barco5++
                                        } else {
                                            //6 agua contra
                                            matrizc[array[0]][array[1]] = 6
                                            actualizarenem()
                                            it.setBackgroundResource(R.drawable.agua)
                                            //cambiar turno
                                            datos = Firebase.database.reference
                                            datos.child("usuarios").child(usuario).child("turno")
                                                .setValue("").addOnSuccessListener { }
                                            datos = Firebase.database.reference
                                            datos.child("usuarios").child(contra).child("turno")
                                                .setValue("1").addOnSuccessListener { }
                                        }
                        datos = Firebase.database.reference
                        datos.child("usuarios").child(usuario).child("bombas").setValue(bombs-1).addOnSuccessListener {  }
                        it.isClickable = false
                    }
                }
                h++
                j++
            }
            i++
        }
    }
    fun posi(l: Int){
        var h=1
        var i=0
        var j=0
        while (h<=70){
            if(j==10){
                j=0
                i++
            }
            if(boton(h)?.id==l){
                array[0]=i
                array[1]=j
            }
            j++
            h++
        }
    }
}