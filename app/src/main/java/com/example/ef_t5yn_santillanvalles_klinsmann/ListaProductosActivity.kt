package com.example.ef_t5yn_santillanvalles_klinsmann

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.ef_t5yn_santillanvalles_klinsmann.adapter.ProductoAdapter


class ListaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val recyclerView = findViewById(R.id.recyclerProducto) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addProducto = findViewById(R.id.btnAddProducto) as FloatingActionButton
        addProducto.setOnClickListener {
            val intent = Intent(this, RegistroProductosActivity::class.java)
            startActivity(intent)
        }
    }
}