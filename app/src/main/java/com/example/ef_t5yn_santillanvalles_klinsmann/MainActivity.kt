package com.example.ef_t5yn_santillanvalles_klinsmann

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityMainBinding
import com.example.ef_t5yn_santillanvalles_klinsmann.presentation.ListadoActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var typeMail : String

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

            if(invalidForm(email, password))
                return@setOnClickListener

            initEvents(typeMail)
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

        val regexGmail = Regex("^[\\w-\\.]+@gmail\\.com\$")
        val regexHotmail = Regex("^[\\w-\\.]+@hotmail\\.com\$")

        if(!email.matches(regexGmail) && !email.matches(regexHotmail)){
            Toast.makeText(this, "Este correo no tiene acceso a la aplicación", Toast.LENGTH_LONG).show()
            return true
        }

        if(email.matches(regexGmail)){
            typeMail = "gmail"
            return false
        }

        if(email.matches(regexHotmail)){
            typeMail = "hotmail"
            return false
        }

        return false
    }

    private fun initEvents(email : String) {
        startActivity(Intent(this, ListadoActivity::class.java).apply {
            putExtra("email", email)
        })
    }
}