package com.example.ef_t5yn_santillanvalles_klinsmann.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ef_t5yn_santillanvalles_klinsmann.R
import com.example.ef_t5yn_santillanvalles_klinsmann.data.ListadoViewModel
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityListadoBinding
import com.example.ef_t5yn_santillanvalles_klinsmann.domain.Producto

class ListadoActivity : AppCompatActivity(), ListadoAdapter.ICard {
    private lateinit var binding : ActivityListadoBinding
    private lateinit var viewModel: ListadoViewModel
    private var listProducto : MutableList<Producto> = ArrayList()
    private lateinit var listadoAdapter: ListadoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues(){
        val email = intent.getStringExtra("email")!!
        if(email == "gmail")
            binding.btnAdd.visibility = View.VISIBLE
        else
            binding.btnAdd.visibility = View.GONE

        println("Email: $email")


        viewModel = ViewModelProvider(this)[ListadoViewModel::class.java]
        listadoAdapter = ListadoAdapter(listProducto, this)
        binding.rvProducto.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvProducto.adapter = listadoAdapter

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AgregarProductoActivity::class.java ))
        }
    }

    private fun initObservers(){
        viewModel.getList.observe(this){
            listadoAdapter.update(it)
        }
        viewModel.getAll()
    }

    override fun onCardClick(item: Producto) {
        TODO("Not yet implemented")
    }
}