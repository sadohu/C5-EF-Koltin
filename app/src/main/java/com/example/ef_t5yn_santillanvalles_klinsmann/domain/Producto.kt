package com.example.ef_t5yn_santillanvalles_klinsmann.domain

import java.io.Serializable

data class Producto (
    val productoId: Int? = 0,
    var nombre : String? = "",
    var descripcion : String? = "",
    var marca: Int? = 0,
    var categoria: Int = 0,
    var precio : Double? = 0.0,
    var foto : String? = "",
    var stock: Int? = 0,
    var cantidadUnitaria : Int? = 0,
    var unidadMedida : Int? = 0,
    var objectId : String? = ""
) : Serializable