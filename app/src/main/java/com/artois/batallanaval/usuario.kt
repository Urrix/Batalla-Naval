package com.artois.batallanaval

import java.io.Serializable

data class usuario(val user: String? = null, val nombre: String? = null,val password: String? = null,val estado: Int? = null,val victorias: Int? = null,val peticion: String? = null, val barcos: Int? = null, val bombas: Int? = null):
    Serializable {
}