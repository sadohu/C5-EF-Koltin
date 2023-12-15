package com.example.ef_t5yn_santillanvalles_klinsmann.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ef_t5yn_santillanvalles_klinsmann.model.Producto
import com.example.ef_t5yn_santillanvalles_klinsmann.R

class ProductoAdapter(
    private val productList: List<Producto>,
    private val clickListener: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vh =
            ProductoViewHolder(layoutInflater.inflate(R.layout.item_productos, parent, false)) {
                clickListener(productList[it])
            }
        return vh
    }
}