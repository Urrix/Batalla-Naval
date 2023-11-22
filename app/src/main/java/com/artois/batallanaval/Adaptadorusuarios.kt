package com.artois.batallanaval

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.personas_activas.view.*

class Adaptadorusuarios(private val mContext: Context, private val listaProductos: List<usuario>): ArrayAdapter<usuario>(mContext,0,listaProductos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.personas_activas, parent,false)
        val producto= listaProductos[position]
        layout.nombre.text = producto.nombre
        layout.precio_producto.text = "Victorias: ${producto.victorias}"
        if(producto.victorias!!<=10){
            layout.imageView.setImageResource(R.drawable.usuario)
        }else{
            layout.imageView.setImageResource(R.drawable.usuarioo)
        }
        return layout
    }
}