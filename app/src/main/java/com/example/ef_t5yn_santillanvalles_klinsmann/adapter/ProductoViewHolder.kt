package com.example.ef_t5yn_santillanvalles_klinsmann.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ef_t5yn_santillanvalles_klinsmann.R
import com.example.ef_t5yn_santillanvalles_klinsmann.model.Producto
class ProductoViewHolder(view: View, position: (Int) -> Unit) : RecyclerView.ViewHolder(view)  {

    val imgProduct = view.findViewById(R.id.imgProducto) as ImageView
    val summaryProducto: TextView = view.findViewById(R.id.lblNombreProducto)
    val constraintRow = view.findViewById(R.id.Productoss) as ConstraintLayout

    init {
        itemView.setOnClickListener {
            position(adapterPosition)
        }
    }

    fun subscribe(producto: Producto) {
        // Setear los valores del producto
        Glide.with(itemView.context).load(producto.foto).into(imgProduct)

        val marca = if (producto.marca === 0) "P & T" else "Razona"
        val unidadM = when (producto.unidadMedida) {
            0 -> "Kg"
            1 -> "mg"
            2 -> "unidad"
            else -> "L"
        }

        // String Templates
        summaryProducto.text = "${producto.nombre} ${producto.cantidadUnitaria} ${unidadM} - ${marca}"

    }
}