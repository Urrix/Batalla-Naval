package com.artois.batallanaval

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.artois.batallanaval.databinding.ActivityAcomodoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Acomodo : AppCompatActivity() {

    lateinit var usuario: String
    lateinit var contra: String
    //bandera para saber que barco se va a colocar
    var bandera=1
    //bandera para saber cuantos barcos a puesto
    var bandera1=0
    //tendremos un objeto de tipo tablero
    var renglon = arrayOf("","","","","","","")
    var matriz=Array(7){IntArray(10)}
    //variable para la conexion con la base
    private lateinit var data: DatabaseReference
    var numerob = "0"
    var hilo: Thread = Thread()
    //binding
    private lateinit var binding: ActivityAcomodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcomodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_acomodo)

        //variables pasadas por intent
        val bundle = intent.extras
        usuario = bundle?.getString("usuario").toString()
        contra = bundle?.getString("contra").toString()
        numerob = bundle?.getString("nbarcos").toString()
        //ponemos la pantalla horizontal
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        hilo = Thread(Runnable {
            while (bandera1<numerob.toInt()){
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
            data = Firebase.database.reference
            data.child("tableros").child(usuario).setValue(tab).addOnSuccessListener {  }
            data = Firebase.database.reference
            data.child("usuarios").child(usuario).child("estado").setValue(3).addOnSuccessListener {}
            val intent8 = Intent(this,Juego::class.java)
            intent8.putExtra("usuario", usuario)
            intent8.putExtra("contra", contra)
            startActivity(intent8)
        })
        hilo.start()
        cargar()
        binding.p1?.setOnClickListener {
            if(bandera==1){
                binding.p1?.setBackgroundResource(R.drawable.uno)
                matriz[0][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p1?.setBackgroundResource(R.drawable.dos)
                    matriz[0][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p1?.setBackgroundResource(R.drawable.dos)
                        matriz[0][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p1?.setBackgroundResource(R.drawable.tres)
                            matriz[0][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p1?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p1?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p1?.isClickable =false
        }
        binding.p2?.setOnClickListener {
            if(bandera==1){
                binding.p2?.setBackgroundResource(R.drawable.uno)
                matriz[0][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p2?.setBackgroundResource(R.drawable.dos)
                    matriz[0][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p2?.setBackgroundResource(R.drawable.dos)
                        matriz[0][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p2?.setBackgroundResource(R.drawable.tres)
                            matriz[0][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p2?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p2?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p2?.isClickable =false
        }
        binding.p3?.setOnClickListener {
            if(bandera==1){
                binding.p3?.setBackgroundResource(R.drawable.uno)
                matriz[0][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p3?.setBackgroundResource(R.drawable.dos)
                    matriz[0][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p3?.setBackgroundResource(R.drawable.dos)
                        matriz[0][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p3?.setBackgroundResource(R.drawable.tres)
                            matriz[0][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p3?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p3?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p3?.isClickable =false
        }
        binding.p4?.setOnClickListener {
            if(bandera==1){
                binding.p4?.setBackgroundResource(R.drawable.uno)
                matriz[0][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p4?.setBackgroundResource(R.drawable.dos)
                    matriz[0][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p4?.setBackgroundResource(R.drawable.dos)
                        matriz[0][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p4?.setBackgroundResource(R.drawable.tres)
                            matriz[0][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p4?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p4?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p4?.isClickable =false
        }
        binding.p5?.setOnClickListener {
            if(bandera==1){
                binding.p5?.setBackgroundResource(R.drawable.uno)
                matriz[0][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p5?.setBackgroundResource(R.drawable.dos)
                    matriz[0][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p5?.setBackgroundResource(R.drawable.dos)
                        matriz[0][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p5?.setBackgroundResource(R.drawable.tres)
                            matriz[0][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p5?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p5?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p5?.isClickable =false
        }
        binding.p6?.setOnClickListener {
            if(bandera==1){
                binding.p6?.setBackgroundResource(R.drawable.uno)
                matriz[0][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p6?.setBackgroundResource(R.drawable.dos)
                    matriz[0][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p6?.setBackgroundResource(R.drawable.dos)
                        matriz[0][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p6?.setBackgroundResource(R.drawable.tres)
                            matriz[0][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p6?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p6?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p6?.isClickable =false
        }
        binding.p7?.setOnClickListener {
            if(bandera==1){
                binding.p7?.setBackgroundResource(R.drawable.uno)
                matriz[0][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p7?.setBackgroundResource(R.drawable.dos)
                    matriz[0][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p7?.setBackgroundResource(R.drawable.dos)
                        matriz[0][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p7?.setBackgroundResource(R.drawable.tres)
                            matriz[0][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p7?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p7?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p7?.isClickable =false
        }
        binding.p8?.setOnClickListener {
            if(bandera==1){
                binding.p8?.setBackgroundResource(R.drawable.uno)
                matriz[0][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p8?.setBackgroundResource(R.drawable.dos)
                    matriz[0][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p8?.setBackgroundResource(R.drawable.dos)
                        matriz[0][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p8?.setBackgroundResource(R.drawable.tres)
                            matriz[0][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p8?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p8?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p8?.isClickable =false
        }
        binding.p9?.setOnClickListener {
            if(bandera==1){
                binding.p9?.setBackgroundResource(R.drawable.uno)
                matriz[0][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p9?.setBackgroundResource(R.drawable.dos)
                    matriz[0][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p9?.setBackgroundResource(R.drawable.dos)
                        matriz[0][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p9?.setBackgroundResource(R.drawable.tres)
                            matriz[0][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p9?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p9?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p9?.isClickable =false
        }
        binding.p10?.setOnClickListener {
            if(bandera==1){
                binding.p10?.setBackgroundResource(R.drawable.uno)
                matriz[0][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p10?.setBackgroundResource(R.drawable.dos)
                    matriz[0][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p10?.setBackgroundResource(R.drawable.dos)
                        matriz[0][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p10?.setBackgroundResource(R.drawable.tres)
                            matriz[0][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p10?.setBackgroundResource(R.drawable.cuatro)
                                matriz[0][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p10?.setBackgroundResource(R.drawable.cinco)
                                    matriz[0][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p10?.isClickable =false
        }
        binding.p11?.setOnClickListener {
            if(bandera==1){
                binding.p11?.setBackgroundResource(R.drawable.uno)
                matriz[1][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p11?.setBackgroundResource(R.drawable.dos)
                    matriz[1][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p11?.setBackgroundResource(R.drawable.dos)
                        matriz[1][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p11?.setBackgroundResource(R.drawable.tres)
                            matriz[1][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p11?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p11?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p11?.isClickable =false
        }
        binding.p12?.setOnClickListener {
            if(bandera==13){
                binding.p12?.setBackgroundResource(R.drawable.uno)
                matriz[1][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p12?.setBackgroundResource(R.drawable.dos)
                    matriz[1][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p12?.setBackgroundResource(R.drawable.dos)
                        matriz[1][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p12?.setBackgroundResource(R.drawable.tres)
                            matriz[1][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p12?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p12?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p12?.isClickable =false
        }
        binding.p13?.setOnClickListener {
            if(bandera==1){
                binding.p13?.setBackgroundResource(R.drawable.uno)
                matriz[1][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p13?.setBackgroundResource(R.drawable.dos)
                    matriz[1][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p13?.setBackgroundResource(R.drawable.dos)
                        matriz[1][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p13?.setBackgroundResource(R.drawable.tres)
                            matriz[1][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p13?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p13?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p13?.isClickable =false
        }
        binding.p14?.setOnClickListener {
            if(bandera==1){
                binding.p14?.setBackgroundResource(R.drawable.uno)
                matriz[1][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p14?.setBackgroundResource(R.drawable.dos)
                    matriz[1][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p14?.setBackgroundResource(R.drawable.dos)
                        matriz[1][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p14?.setBackgroundResource(R.drawable.tres)
                            matriz[1][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p14?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p14?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p14?.isClickable =false
        }
        binding.p15?.setOnClickListener {
            if(bandera==1){
                binding.p15?.setBackgroundResource(R.drawable.uno)
                matriz[1][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p15?.setBackgroundResource(R.drawable.dos)
                    matriz[1][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p15?.setBackgroundResource(R.drawable.dos)
                        matriz[1][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p15?.setBackgroundResource(R.drawable.tres)
                            matriz[1][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p15?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p15?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p15?.isClickable =false
        }
        binding.p16?.setOnClickListener {
            if(bandera==1){
                binding.p16?.setBackgroundResource(R.drawable.uno)
                matriz[1][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p16?.setBackgroundResource(R.drawable.dos)
                    matriz[1][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p16?.setBackgroundResource(R.drawable.dos)
                        matriz[1][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p16?.setBackgroundResource(R.drawable.tres)
                            matriz[1][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p16?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p16?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p16?.isClickable =false
        }
        binding.p17?.setOnClickListener {
            if(bandera==1){
                binding.p17?.setBackgroundResource(R.drawable.uno)
                matriz[1][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p17?.setBackgroundResource(R.drawable.dos)
                    matriz[1][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p17?.setBackgroundResource(R.drawable.dos)
                        matriz[1][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p17?.setBackgroundResource(R.drawable.tres)
                            matriz[1][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p17?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p17?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p17?.isClickable =false
        }
        binding.p18?.setOnClickListener {
            if(bandera==1){
                binding.p18?.setBackgroundResource(R.drawable.uno)
                matriz[1][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p18?.setBackgroundResource(R.drawable.dos)
                    matriz[1][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p18?.setBackgroundResource(R.drawable.dos)
                        matriz[1][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p18?.setBackgroundResource(R.drawable.tres)
                            matriz[1][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p18?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p18?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p18?.isClickable =false
        }
        binding.p19?.setOnClickListener {
            if(bandera==1){
                binding.p19?.setBackgroundResource(R.drawable.uno)
                matriz[1][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p19?.setBackgroundResource(R.drawable.dos)
                    matriz[1][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p19?.setBackgroundResource(R.drawable.dos)
                        matriz[1][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p19?.setBackgroundResource(R.drawable.tres)
                            matriz[1][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p19?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p19?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p19?.isClickable =false
        }
        binding.p20?.setOnClickListener {
            if(bandera==1){
                binding.p20?.setBackgroundResource(R.drawable.uno)
                matriz[1][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p20?.setBackgroundResource(R.drawable.dos)
                    matriz[1][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p20?.setBackgroundResource(R.drawable.dos)
                        matriz[1][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p20?.setBackgroundResource(R.drawable.tres)
                            matriz[1][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p20?.setBackgroundResource(R.drawable.cuatro)
                                matriz[1][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p20?.setBackgroundResource(R.drawable.cinco)
                                    matriz[1][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p20?.isClickable =false
        }
        binding.p21?.setOnClickListener {
            if(bandera==1){
                binding.p21?.setBackgroundResource(R.drawable.uno)
                matriz[2][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p21?.setBackgroundResource(R.drawable.dos)
                    matriz[2][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p21?.setBackgroundResource(R.drawable.dos)
                        matriz[2][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p21?.setBackgroundResource(R.drawable.tres)
                            matriz[0][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p21?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p21?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p21?.isClickable =false
        }
        binding.p22?.setOnClickListener {
            if(bandera==1){
                binding.p22?.setBackgroundResource(R.drawable.uno)
                matriz[2][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p22?.setBackgroundResource(R.drawable.dos)
                    matriz[2][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p22?.setBackgroundResource(R.drawable.dos)
                        matriz[2][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p22?.setBackgroundResource(R.drawable.tres)
                            matriz[2][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p22?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p22?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p22?.isClickable =false
        }
        binding.p23?.setOnClickListener {
            if(bandera==1){
                binding.p23?.setBackgroundResource(R.drawable.uno)
                matriz[2][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p23?.setBackgroundResource(R.drawable.dos)
                    matriz[2][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p23?.setBackgroundResource(R.drawable.dos)
                        matriz[2][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p23?.setBackgroundResource(R.drawable.tres)
                            matriz[2][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p23?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p23?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p23?.isClickable =false
        }
        binding.p24?.setOnClickListener {
            if(bandera==1){
                binding.p24?.setBackgroundResource(R.drawable.uno)
                matriz[2][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p24?.setBackgroundResource(R.drawable.dos)
                    matriz[2][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p24?.setBackgroundResource(R.drawable.dos)
                        matriz[2][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p24?.setBackgroundResource(R.drawable.tres)
                            matriz[2][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p24?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p24?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p24?.isClickable =false
        }
        binding.p25?.setOnClickListener {
            if(bandera==1){
                binding.p25?.setBackgroundResource(R.drawable.uno)
                matriz[2][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p25?.setBackgroundResource(R.drawable.dos)
                    matriz[2][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p25?.setBackgroundResource(R.drawable.dos)
                        matriz[2][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p25?.setBackgroundResource(R.drawable.tres)
                            matriz[2][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p25?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p25?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p25?.isClickable =false
        }
        binding.p26?.setOnClickListener {
            if(bandera==1){
                binding.p26?.setBackgroundResource(R.drawable.uno)
                matriz[2][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p26?.setBackgroundResource(R.drawable.dos)
                    matriz[2][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p26?.setBackgroundResource(R.drawable.dos)
                        matriz[2][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p26?.setBackgroundResource(R.drawable.tres)
                            matriz[2][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p26?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p26?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p26?.isClickable =false
        }
        binding.p27?.setOnClickListener {
            if(bandera==1){
                binding.p27?.setBackgroundResource(R.drawable.uno)
                matriz[2][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p27?.setBackgroundResource(R.drawable.dos)
                    matriz[2][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p27?.setBackgroundResource(R.drawable.dos)
                        matriz[2][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p27?.setBackgroundResource(R.drawable.tres)
                            matriz[2][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p27?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p27?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p27?.isClickable =false
        }
        binding.p28?.setOnClickListener {
            if(bandera==1){
                binding.p28?.setBackgroundResource(R.drawable.uno)
                matriz[2][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p28?.setBackgroundResource(R.drawable.dos)
                    matriz[2][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p28?.setBackgroundResource(R.drawable.dos)
                        matriz[2][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p28?.setBackgroundResource(R.drawable.tres)
                            matriz[2][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p28?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p28?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p28?.isClickable =false
        }
        binding.p29?.setOnClickListener {
            if(bandera==1){
                binding.p29?.setBackgroundResource(R.drawable.uno)
                matriz[2][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p29?.setBackgroundResource(R.drawable.dos)
                    matriz[2][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p29?.setBackgroundResource(R.drawable.dos)
                        matriz[2][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p29?.setBackgroundResource(R.drawable.tres)
                            matriz[2][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p29?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p29?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p29?.isClickable =false
        }
        binding.p30?.setOnClickListener {
            if(bandera==1){
                binding.p30?.setBackgroundResource(R.drawable.uno)
                matriz[2][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p30?.setBackgroundResource(R.drawable.dos)
                    matriz[2][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p30?.setBackgroundResource(R.drawable.dos)
                        matriz[2][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p30?.setBackgroundResource(R.drawable.tres)
                            matriz[2][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p30?.setBackgroundResource(R.drawable.cuatro)
                                matriz[2][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p30?.setBackgroundResource(R.drawable.cinco)
                                    matriz[2][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p30?.isClickable =false
        }
        binding.p31?.setOnClickListener {
            if(bandera==1){
                binding.p31?.setBackgroundResource(R.drawable.uno)
                matriz[3][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p31?.setBackgroundResource(R.drawable.dos)
                    matriz[3][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p31?.setBackgroundResource(R.drawable.dos)
                        matriz[3][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p31?.setBackgroundResource(R.drawable.tres)
                            matriz[3][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p31?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p31?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p31?.isClickable =false
        }
        binding.p32?.setOnClickListener {
            if(bandera==1){
                binding.p32?.setBackgroundResource(R.drawable.uno)
                matriz[3][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p32?.setBackgroundResource(R.drawable.dos)
                    matriz[3][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p32?.setBackgroundResource(R.drawable.dos)
                        matriz[3][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p32?.setBackgroundResource(R.drawable.tres)
                            matriz[3][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p32?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p32?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p32?.isClickable =false
        }
        binding.p33?.setOnClickListener {
            if(bandera==1){
                binding.p33?.setBackgroundResource(R.drawable.uno)
                matriz[3][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p33?.setBackgroundResource(R.drawable.dos)
                    matriz[3][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p33?.setBackgroundResource(R.drawable.dos)
                        matriz[3][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p33?.setBackgroundResource(R.drawable.tres)
                            matriz[3][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p33?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p33?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p33?.isClickable =false
        }
        binding.p34?.setOnClickListener {
            if(bandera==1){
                binding.p34?.setBackgroundResource(R.drawable.uno)
                matriz[3][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p34?.setBackgroundResource(R.drawable.dos)
                    matriz[3][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p34?.setBackgroundResource(R.drawable.dos)
                        matriz[3][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p34?.setBackgroundResource(R.drawable.tres)
                            matriz[3][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p34?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p34?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p34?.isClickable =false
        }
        binding.p35?.setOnClickListener {
            if(bandera==1){
                binding.p35?.setBackgroundResource(R.drawable.uno)
                matriz[3][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p35?.setBackgroundResource(R.drawable.dos)
                    matriz[3][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p35?.setBackgroundResource(R.drawable.dos)
                        matriz[3][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p35?.setBackgroundResource(R.drawable.tres)
                            matriz[3][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p35?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p35?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p35?.isClickable =false
        }
        binding.p36?.setOnClickListener {
            if(bandera==1){
                binding.p36?.setBackgroundResource(R.drawable.uno)
                matriz[3][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p36?.setBackgroundResource(R.drawable.dos)
                    matriz[3][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p36?.setBackgroundResource(R.drawable.dos)
                        matriz[3][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p36?.setBackgroundResource(R.drawable.tres)
                            matriz[3][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p36?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p36?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p36?.isClickable =false
        }
        binding.p37?.setOnClickListener {
            if(bandera==1){
                binding.p37?.setBackgroundResource(R.drawable.uno)
                matriz[3][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p37?.setBackgroundResource(R.drawable.dos)
                    matriz[3][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p37?.setBackgroundResource(R.drawable.dos)
                        matriz[3][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p37?.setBackgroundResource(R.drawable.tres)
                            matriz[3][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p37?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p37?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p37?.isClickable =false
        }
        binding.p38?.setOnClickListener {
            if(bandera==1){
                binding.p38?.setBackgroundResource(R.drawable.uno)
                matriz[3][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p38?.setBackgroundResource(R.drawable.dos)
                    matriz[3][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p38?.setBackgroundResource(R.drawable.dos)
                        matriz[3][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p38?.setBackgroundResource(R.drawable.tres)
                            matriz[3][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p38?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p38?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p38?.isClickable =false
        }
        binding.p39?.setOnClickListener {
            if(bandera==1){
                binding.p39?.setBackgroundResource(R.drawable.uno)
                matriz[3][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p39?.setBackgroundResource(R.drawable.dos)
                    matriz[3][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p39?.setBackgroundResource(R.drawable.dos)
                        matriz[3][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p39?.setBackgroundResource(R.drawable.tres)
                            matriz[3][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p39?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p39?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p39?.isClickable =false
        }
        binding.p40?.setOnClickListener {
            if(bandera==1){
                binding.p40?.setBackgroundResource(R.drawable.uno)
                matriz[3][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p40?.setBackgroundResource(R.drawable.dos)
                    matriz[3][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p40?.setBackgroundResource(R.drawable.dos)
                        matriz[3][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p40?.setBackgroundResource(R.drawable.tres)
                            matriz[3][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p40?.setBackgroundResource(R.drawable.cuatro)
                                matriz[3][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p40?.setBackgroundResource(R.drawable.cinco)
                                    matriz[3][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p40?.isClickable =false
        }
        binding.p41?.setOnClickListener {
            if(bandera==1){
                binding.p41?.setBackgroundResource(R.drawable.uno)
                matriz[4][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p41?.setBackgroundResource(R.drawable.dos)
                    matriz[4][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p41?.setBackgroundResource(R.drawable.dos)
                        matriz[4][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p41?.setBackgroundResource(R.drawable.tres)
                            matriz[4][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p41?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p41?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p41?.isClickable =false
        }
        binding.p42?.setOnClickListener {
            if(bandera==1){
                binding.p42?.setBackgroundResource(R.drawable.uno)
                matriz[4][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p42?.setBackgroundResource(R.drawable.dos)
                    matriz[4][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p42?.setBackgroundResource(R.drawable.dos)
                        matriz[4][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p42?.setBackgroundResource(R.drawable.tres)
                            matriz[4][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p42?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p42?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p42?.isClickable =false
        }
        binding.p43?.setOnClickListener {
            if(bandera==1){
                binding.p33?.setBackgroundResource(R.drawable.uno)
                matriz[4][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p43?.setBackgroundResource(R.drawable.dos)
                    matriz[4][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p43?.setBackgroundResource(R.drawable.dos)
                        matriz[4][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p43?.setBackgroundResource(R.drawable.tres)
                            matriz[4][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p43?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p43?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p43?.isClickable =false
        }
        binding.p44?.setOnClickListener {
            if(bandera==1){
                binding.p44?.setBackgroundResource(R.drawable.uno)
                matriz[4][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p44?.setBackgroundResource(R.drawable.dos)
                    matriz[4][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p44?.setBackgroundResource(R.drawable.dos)
                        matriz[4][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p44?.setBackgroundResource(R.drawable.tres)
                            matriz[4][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p44?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p44?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p44?.isClickable =false
        }
        binding.p45?.setOnClickListener {
            if(bandera==1){
                binding.p45?.setBackgroundResource(R.drawable.uno)
                matriz[4][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p45?.setBackgroundResource(R.drawable.dos)
                    matriz[4][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p45?.setBackgroundResource(R.drawable.dos)
                        matriz[4][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p45?.setBackgroundResource(R.drawable.tres)
                            matriz[4][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p45?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p45?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p45?.isClickable =false
        }
        binding.p46?.setOnClickListener {
            if(bandera==1){
                binding.p46?.setBackgroundResource(R.drawable.uno)
                matriz[4][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p46?.setBackgroundResource(R.drawable.dos)
                    matriz[4][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p46?.setBackgroundResource(R.drawable.dos)
                        matriz[4][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p46?.setBackgroundResource(R.drawable.tres)
                            matriz[4][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p46?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p46?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p46?.isClickable =false
        }
        binding.p47?.setOnClickListener {
            if(bandera==1){
                binding.p47?.setBackgroundResource(R.drawable.uno)
                matriz[4][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p47?.setBackgroundResource(R.drawable.dos)
                    matriz[4][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p47?.setBackgroundResource(R.drawable.dos)
                        matriz[4][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p47?.setBackgroundResource(R.drawable.tres)
                            matriz[4][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p47?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p47?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p47?.isClickable =false
        }
        binding.p48?.setOnClickListener {
            if(bandera==1){
                binding.p48?.setBackgroundResource(R.drawable.uno)
                matriz[4][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p48?.setBackgroundResource(R.drawable.dos)
                    matriz[4][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p48?.setBackgroundResource(R.drawable.dos)
                        matriz[4][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p48?.setBackgroundResource(R.drawable.tres)
                            matriz[4][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p48?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p48?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p48?.isClickable =false
        }
        binding.p49?.setOnClickListener {
            if(bandera==1){
                binding.p49?.setBackgroundResource(R.drawable.uno)
                matriz[4][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p49?.setBackgroundResource(R.drawable.dos)
                    matriz[4][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p49?.setBackgroundResource(R.drawable.dos)
                        matriz[4][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p49?.setBackgroundResource(R.drawable.tres)
                            matriz[4][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p49?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p49?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p49?.isClickable =false
        }
        binding.p50?.setOnClickListener {
            if(bandera==1){
                binding.p50?.setBackgroundResource(R.drawable.uno)
                matriz[4][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p50?.setBackgroundResource(R.drawable.dos)
                    matriz[4][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p50?.setBackgroundResource(R.drawable.dos)
                        matriz[4][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p50?.setBackgroundResource(R.drawable.tres)
                            matriz[4][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p50?.setBackgroundResource(R.drawable.cuatro)
                                matriz[4][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p50?.setBackgroundResource(R.drawable.cinco)
                                    matriz[4][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p50?.isClickable =false
        }
        binding.p51?.setOnClickListener {
            if(bandera==1){
                binding.p51?.setBackgroundResource(R.drawable.uno)
                matriz[5][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p51?.setBackgroundResource(R.drawable.dos)
                    matriz[5][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p51?.setBackgroundResource(R.drawable.dos)
                        matriz[5][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p51?.setBackgroundResource(R.drawable.tres)
                            matriz[5][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p51?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p51?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p51?.isClickable =false
        }
        binding.p52?.setOnClickListener {
            if(bandera==1){
                binding.p52?.setBackgroundResource(R.drawable.uno)
                matriz[5][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p52?.setBackgroundResource(R.drawable.dos)
                    matriz[5][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p52?.setBackgroundResource(R.drawable.dos)
                        matriz[5][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p52?.setBackgroundResource(R.drawable.tres)
                            matriz[5][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p52?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p52?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p52?.isClickable =false
        }
        binding.p53?.setOnClickListener {
            if(bandera==1){
                binding.p53?.setBackgroundResource(R.drawable.uno)
                matriz[5][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p53?.setBackgroundResource(R.drawable.dos)
                    matriz[5][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p53?.setBackgroundResource(R.drawable.dos)
                        matriz[5][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p53?.setBackgroundResource(R.drawable.tres)
                            matriz[5][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p53?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p53?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p53?.isClickable =false
        }
        binding.p54?.setOnClickListener {
            if(bandera==1){
                binding.p54?.setBackgroundResource(R.drawable.uno)
                matriz[5][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p54?.setBackgroundResource(R.drawable.dos)
                    matriz[5][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p54?.setBackgroundResource(R.drawable.dos)
                        matriz[5][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p54?.setBackgroundResource(R.drawable.tres)
                            matriz[5][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p54?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p54?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p54?.isClickable =false
        }
        binding.p55?.setOnClickListener {
            if(bandera==1){
                binding.p55?.setBackgroundResource(R.drawable.uno)
                matriz[5][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p55?.setBackgroundResource(R.drawable.dos)
                    matriz[5][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p55?.setBackgroundResource(R.drawable.dos)
                        matriz[5][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p55?.setBackgroundResource(R.drawable.tres)
                            matriz[5][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p55?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p55?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p55?.isClickable =false
        }
        binding.p56?.setOnClickListener {
            if(bandera==1){
                binding.p56?.setBackgroundResource(R.drawable.uno)
                matriz[5][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p56?.setBackgroundResource(R.drawable.dos)
                    matriz[5][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p56?.setBackgroundResource(R.drawable.dos)
                        matriz[5][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p56?.setBackgroundResource(R.drawable.tres)
                            matriz[5][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p56?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p56?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p56?.isClickable =false
        }
        binding.p57?.setOnClickListener {
            if(bandera==1){
                binding.p57?.setBackgroundResource(R.drawable.uno)
                matriz[5][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p57?.setBackgroundResource(R.drawable.dos)
                    matriz[5][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p57?.setBackgroundResource(R.drawable.dos)
                        matriz[5][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p57?.setBackgroundResource(R.drawable.tres)
                            matriz[5][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p57?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p57?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p57?.isClickable =false
        }
        binding.p58?.setOnClickListener {
            if(bandera==1){
                binding.p58?.setBackgroundResource(R.drawable.uno)
                matriz[5][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p58?.setBackgroundResource(R.drawable.dos)
                    matriz[5][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p58?.setBackgroundResource(R.drawable.dos)
                        matriz[5][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p58?.setBackgroundResource(R.drawable.tres)
                            matriz[5][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p58?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p58?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p58?.isClickable =false
        }
        binding.p59?.setOnClickListener {
            if(bandera==1){
                binding.p59?.setBackgroundResource(R.drawable.uno)
                matriz[5][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p59?.setBackgroundResource(R.drawable.dos)
                    matriz[5][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p59?.setBackgroundResource(R.drawable.dos)
                        matriz[5][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p59?.setBackgroundResource(R.drawable.tres)
                            matriz[5][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p59?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p59?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p59?.isClickable =false
        }
        binding.p60?.setOnClickListener {
            if(bandera==1){
                binding.p60?.setBackgroundResource(R.drawable.uno)
                matriz[5][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p60?.setBackgroundResource(R.drawable.dos)
                    matriz[5][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p60?.setBackgroundResource(R.drawable.dos)
                        matriz[5][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p60?.setBackgroundResource(R.drawable.tres)
                            matriz[5][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p60?.setBackgroundResource(R.drawable.cuatro)
                                matriz[5][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p60?.setBackgroundResource(R.drawable.cinco)
                                    matriz[5][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p60?.isClickable =false
        }
        binding.p61?.setOnClickListener {
            if(bandera==1){
                binding.p61?.setBackgroundResource(R.drawable.uno)
                matriz[6][0]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p61?.setBackgroundResource(R.drawable.dos)
                    matriz[6][0]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p61?.setBackgroundResource(R.drawable.dos)
                        matriz[6][0]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p61?.setBackgroundResource(R.drawable.tres)
                            matriz[6][0]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p61?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][0]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p61?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][0]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p61?.isClickable =false
        }
        binding.p62?.setOnClickListener {
            if(bandera==1){
                binding.p62?.setBackgroundResource(R.drawable.uno)
                matriz[6][1]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p62?.setBackgroundResource(R.drawable.dos)
                    matriz[6][1]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p62?.setBackgroundResource(R.drawable.dos)
                        matriz[6][1]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p62?.setBackgroundResource(R.drawable.tres)
                            matriz[6][1]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p62?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][1]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p62?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][1]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p62?.isClickable =false
        }
        binding.p63?.setOnClickListener {
            if(bandera==1){
                binding.p63?.setBackgroundResource(R.drawable.uno)
                matriz[6][2]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p63?.setBackgroundResource(R.drawable.dos)
                    matriz[6][2]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p63?.setBackgroundResource(R.drawable.dos)
                        matriz[6][2]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera==4 || bandera==5 ||bandera==6){
                            binding.p63?.setBackgroundResource(R.drawable.tres)
                            matriz[6][2]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p63?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][2]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p63?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][2]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p63?.isClickable =false
        }
        binding.p64?.setOnClickListener {
            if(bandera==1){
                binding.p64?.setBackgroundResource(R.drawable.uno)
                matriz[6][3]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p64?.setBackgroundResource(R.drawable.dos)
                    matriz[6][3]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p64?.setBackgroundResource(R.drawable.dos)
                        matriz[6][3]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p64?.setBackgroundResource(R.drawable.tres)
                            matriz[6][3]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p64?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][3]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p64?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][3]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p64?.isClickable =false
        }
        binding.p65?.setOnClickListener {
            if(bandera==1){
                binding.p65?.setBackgroundResource(R.drawable.uno)
                matriz[6][4]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p65?.setBackgroundResource(R.drawable.dos)
                    matriz[6][4]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p65?.setBackgroundResource(R.drawable.dos)
                        matriz[6][4]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p65?.setBackgroundResource(R.drawable.tres)
                            matriz[6][4]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p65?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][4]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p65?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][4]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p65?.isClickable =false
        }
        binding.p66?.setOnClickListener {
            if(bandera==1){
                binding.p66?.setBackgroundResource(R.drawable.uno)
                matriz[6][5]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p66?.setBackgroundResource(R.drawable.dos)
                    matriz[6][5]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p66?.setBackgroundResource(R.drawable.dos)
                        matriz[6][5]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p66?.setBackgroundResource(R.drawable.tres)
                            matriz[6][5]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p66?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][5]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p66?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][5]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p66?.isClickable =false
        }
        binding.p67?.setOnClickListener {
            if(bandera==1){
                binding.p67?.setBackgroundResource(R.drawable.uno)
                matriz[6][6]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p67?.setBackgroundResource(R.drawable.dos)
                    matriz[6][6]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p67?.setBackgroundResource(R.drawable.dos)
                        matriz[6][6]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p67?.setBackgroundResource(R.drawable.tres)
                            matriz[6][6]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p67?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][6]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p67?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][6]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p67?.isClickable =false
        }
        binding.p68?.setOnClickListener {
            if(bandera==1){
                binding.p68?.setBackgroundResource(R.drawable.uno)
                matriz[6][7]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p68?.setBackgroundResource(R.drawable.dos)
                    matriz[6][7]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p68?.setBackgroundResource(R.drawable.dos)
                        matriz[6][7]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p68?.setBackgroundResource(R.drawable.tres)
                            matriz[6][7]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p68?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][7]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p68?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][7]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p68?.isClickable =false
        }
        binding.p69?.setOnClickListener {
            if(bandera==1){
                binding.p69?.setBackgroundResource(R.drawable.uno)
                matriz[6][8]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p69?.setBackgroundResource(R.drawable.dos)
                    matriz[6][8]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p69?.setBackgroundResource(R.drawable.dos)
                        matriz[6][8]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p69?.setBackgroundResource(R.drawable.tres)
                            matriz[6][8]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p69?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][8]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p69?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][8]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p69?.isClickable =false
        }
        binding.p70?.setOnClickListener {
            if(bandera==1){
                binding.p70?.setBackgroundResource(R.drawable.uno)
                matriz[6][9]=1
                bandera1=1
                binding.lancha?.isVisible = false
                bandera++
            }else
                if(bandera==2){
                    binding.p70?.setBackgroundResource(R.drawable.dos)
                    matriz[6][9]=2
                    bandera++
                }else
                    if(bandera==3){
                        binding.p70?.setBackgroundResource(R.drawable.dos)
                        matriz[6][9]=2
                        bandera1=2
                        binding.crucero?.isVisible = false
                        bandera++
                    }else
                        if(bandera== 4 || bandera==5 ||bandera==6){
                            binding.p70?.setBackgroundResource(R.drawable.tres)
                            matriz[6][9]=3
                            if(bandera==6){
                                binding.submarino?.isVisible = false
                                bandera1=3
                            }
                            bandera++
                        }else
                            if (bandera==7 || bandera==8 || bandera==9 || bandera==10){
                                binding.p70?.setBackgroundResource(R.drawable.cuatro)
                                matriz[6][9]=4
                                if(bandera==10){
                                    binding.buque?.isVisible = false
                                    bandera1=4
                                }
                                bandera++
                            }else
                                if(bandera==11 || bandera==12 || bandera==13 || bandera==14 || bandera==15){
                                    binding.p70?.setBackgroundResource(R.drawable.cinco)
                                    matriz[6][9]=5
                                    if(bandera==15){
                                        binding.aviones?.isVisible = false
                                        bandera1=5
                                    }
                                    bandera++
                                }
            binding.p70?.isClickable =false
        }
    }
    fun cargar(){
            if(numerob=="1"){
                binding.lancha?.isVisible = true
            }
            if(numerob=="2"){
                binding.lancha?.isVisible = true
                binding.crucero?.isVisible = true
            }
            if(numerob=="3"){
                binding.lancha?.isVisible = true
                binding.crucero?.isVisible = true
                binding.submarino?.isVisible = true
            }
            if(numerob=="4"){
                binding.lancha?.isVisible = true
                binding.crucero?.isVisible = true
                binding.submarino?.isVisible = true
                binding.buque?.isVisible = true
            }
            if(numerob=="5"){
                binding.lancha?.isVisible = true
                binding.crucero?.isVisible = true
                binding.submarino?.isVisible = true
                binding.buque?.isVisible = true
                binding.aviones?.isVisible = true
            }
        }
}