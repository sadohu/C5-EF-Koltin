package com.example.ef_t5yn_santillanvalles_klinsmann.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ef_t5yn_santillanvalles_klinsmann.R
import com.example.ef_t5yn_santillanvalles_klinsmann.domain.Producto

class ListadoAdapter(var items : MutableList<Producto>, var iCard : ICard) : RecyclerView.Adapter<ListadoAdapter.ViewHolder>(){
    interface ICard{
        fun onCardClick(item: Producto)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titulo : TextView = itemView.findViewById(R.id.rvProductoTitulo)
        val descripcion : TextView = itemView.findViewById(R.id.rvProductoDescripcion)
        val img : ImageFilterView = itemView.findViewById(R.id.rvProductoImg)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iCard.onCardClick(items[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListadoAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListadoAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.titulo.text = item.nombre
        holder.descripcion.text = item.descripcion
        Glide.with(holder.itemView.context)
            .load(item.foto)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems : List<Producto>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

}