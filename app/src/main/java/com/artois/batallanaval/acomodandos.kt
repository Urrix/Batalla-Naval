package com.artois.batallanaval

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.core.view.isVisible
import com.artois.batallanaval.databinding.ActivityAcomodandosBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class acomodandos : AppCompatActivity() {
    private lateinit var datos: DatabaseReference
    lateinit var usuario: String
    var hilo: Thread = Thread()
    //bandera para saber que barco se va a colocar
    var bandera=1
    //bandera para saber cuantos barcos a puesto
    var bandera1=0
    //tendremos un objeto de tipo tablero
    var renglon = arrayOf("","","","","","","")
    var matriz=Array(7){IntArray(10)}
    var matrizpc=Array(7){IntArray(10)}
    var array = arrayOf(0,0)
    var numerob = "0"
    var x=0
    var y=0
    var uno=0
    var dos=0
    var tres=0
    var cuatro=0
    var cinco=0
    //bindingA
    private lateinit var binding: ActivityAcomodandosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_acomodandos)
        binding = ActivityAcomodandosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ponemos la pantalla horizontal
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        //usuario enviado desde intent
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        botones()
        cargar()
        hilo = Thread(Runnable {
            while (bandera1<5){
                Thread.sleep(100)
            }
            //si ya estan los barcos
            bandera=17
            //Intent al juego
            //guardar tablero
            var i=0
            var j=0
            while(i<7){
                j=0
                while(j<10){
                    renglon[i]=renglon[i]+matriz[i][j].toString()
                    j++
                }
                i++
            }
            var tab = Tablero(renglon[0],renglon[1],renglon[2],renglon[3],renglon[4],renglon[5],renglon[6])
            datos = Firebase.database.reference
            datos.child("tableros").child(usuario).setValue(tab).addOnSuccessListener {  }
            datos = Firebase.database.reference
            datos.child("usuarios").child(usuario).child("turno").setValue(1).addOnSuccessListener {}
            tableropc()
        })
        hilo.start()
    }
    fun botones(){
        var i=0
        var j=0
        var h=1
        while (i<=6){
            j=0
            while (j<=9){
                boton(h)?.setOnClickListener{
                    posi(it.id)
                    if(bandera==1){
                        it.setBackgroundResource(R.drawable.uno)
                        matriz[array[0]][array[1]]=1
                        bandera1=1
                        binding.lancha?.isVisible = false
                        bandera++
                    }else
                        if(bandera==2){
                            it.setBackgroundResource(R.drawable.dos)
                            matriz[array[0]][array[1]]=2
                            bandera++
                        }else
                            if(bandera==3){
                                it.setBackgroundResource(R.drawable.dos)
                                matriz[array[0]][array[1]]=2
                                bandera1=2
                                binding.crucero?.isVisible = false
                                bandera++
                            }else
                                if(bandera== 4 || bandera==5 ||bandera==6){
                                    it.setBackgroundResource(R.drawable.tres)
                                    matriz[array[0]][array[1]]=3
                                    if(bandera==6){
                                        binding.submarino?.isVisible = false
                                        bandera1=3
                                    }
                                    bandera++
                                }else
                                    if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                        it.setBackgroundResource(R.drawable.cuatro)
                                        matriz[array[0]][array[1]]=4
                                        if(bandera==10){
                                            binding.buque?.isVisible = false
                                            bandera1=4
                                        }
                                        bandera++
                                    }else
                                        if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                            it.setBackgroundResource(R.drawable.cinco)
                                            matriz[array[0]][array[1]]=5
                                            if(bandera==15){
                                                binding.aviones?.isVisible = false
                                                bandera1=5
                                            }
                                            bandera++
                                        }
                    it.isClickable =false
                }
                h++
                j++
            }
            i++
        }
    }
    fun boton(i: Int): ImageButton? {
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
    fun cargar(){
        binding.lancha?.isVisible = true
        binding.crucero?.isVisible = true
        binding.submarino?.isVisible = true
        binding.buque?.isVisible = true
        binding.aviones?.isVisible = true
    }
    fun tableropc(){
        var i=0
        while (i<5){
            posicion()
            if(i==0){
                matrizpc[x][y]=1
                uno=x
                i++
            }else
                if(i==1){
                    if(x==uno){
                    }else{
                        if(y<9){
                            matrizpc[x][y]=2
                            matrizpc[x][y+1]=2
                            dos=x
                            i++
                        }
                    }
                }else
                    if(i==2){
                        if(x==uno || x==dos){
                        }else{
                            if(y<8){
                                matrizpc[x][y]=3
                                matrizpc[x][y+1]=3
                                matrizpc[x][y+2]=3
                                tres=x
                                i++
                            }
                        }
                    }else
                        if (i==3){
                            if(x==uno || x==dos || x==tres){
                            }else{
                                if(y<7){
                                    matrizpc[x][y]=4
                                    matrizpc[x][y+1]=4
                                    matrizpc[x][y+2]=4
                                    matrizpc[x][y+3]=4
                                    cuatro=x
                                    i++
                                }
                            }
                        }else
                            if(i==4){
                                if(x==uno || x==dos || x==tres || x==cuatro){
                                }else{
                                    if(y<6){
                                        matrizpc[x][y]=5
                                        matrizpc[x][y+1]=5
                                        matrizpc[x][y+2]=5
                                        matrizpc[x][y+3]=5
                                        matrizpc[x][y+4]=5
                                        cinco=x
                                        i++
                                    }
                                }
                            }
        }
        //guardar tablero
        i=0
        while (i<7){
            renglon[i]=""
            i++
        }
        i=0
        var j=0
        while(i<7){
            j=0
            while(j<10){
                renglon[i]=renglon[i]+matrizpc[i][j].toString()
                j++
            }
            i++
        }
        var tab = Tablero(renglon[0],renglon[1],renglon[2],renglon[3],renglon[4],renglon[5],renglon[6])
        datos = Firebase.database.reference
        datos.child("tableros").child("root").setValue(tab).addOnSuccessListener {  }
        datos = Firebase.database.reference
        datos.child("usuarios").child(usuario).child("turno").setValue("1").addOnSuccessListener {  }
        val intent8 = Intent(this,jugarsolo::class.java)
        intent8.putExtra("usuario", usuario)
        startActivity(intent8)
    }
    fun posicion() {
        var random1=0
        var random2=0
        val numeros= 0..6
        random1= numeros.random()
        val numeros2= 0..9
        random2= numeros2.random()
        x=random1
        y=random2
    }
}