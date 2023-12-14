package com.example.ef_t5yn_santillanvalles_klinsmann.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ef_t5yn_santillanvalles_klinsmann.R
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityListadoBinding

class ListadoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        val email = intent.getStringExtra("email")!!
        println("Email: $email")
    }
}