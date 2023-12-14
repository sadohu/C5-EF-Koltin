package com.example.ef_t5yn_santillanvalles_klinsmann

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        binding.btnIngresar.setOnClickListener {
            val email = binding.etUsuario.text.toString()
            val password = binding.etPassword.text.toString()
            println("email: $email")
            println("password: $password")

            if(invalidForm(email, password))
                return@setOnClickListener


            val regexGmail = Regex("^[\\w-\\.]+@gmail\\.com\$")
            val regexHotmail = Regex("^[\\w-\\.]+@hotmail\\.com\$")

            if(email.matches(regexGmail)){
                Toast.makeText(this, "Correo de Gmail", Toast.LENGTH_LONG).show()
            }

            if(email.matches(regexHotmail)){
                Toast.makeText(this, "Correo de Hotmail", Toast.LENGTH_LONG).show()
            }


        }






    }

    private fun invalidForm(email : String, password : String) : Boolean{
        if(email.isNullOrBlank() || password.isNullOrBlank()){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show()
            return true
        }

        val regexEmail = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

        if(!email.matches(regexEmail)){
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_LONG).show()
            return true
        }

        return false
    }
}