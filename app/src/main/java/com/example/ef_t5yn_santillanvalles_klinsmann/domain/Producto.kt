package com.example.ef_t5yn_santillanvalles_klinsmann.domain

data class Producto (
    var nombre : String? = "",
    var descripcion : String? = "",
    var img : String? = "",
    var precio : Double? = 0.0,
    var cantidad : Int? = 0,
    var id : String? = ""
)