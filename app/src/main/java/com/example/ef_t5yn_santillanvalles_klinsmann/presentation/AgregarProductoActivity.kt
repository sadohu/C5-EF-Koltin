package com.example.ef_t5yn_santillanvalles_klinsmann.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ef_t5yn_santillanvalles_klinsmann.R
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityAgregarProductoBinding

class AgregarProductoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAgregarProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues(){

    }

    private fun initObservers(){

    }
}