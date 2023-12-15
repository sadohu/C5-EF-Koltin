package com.example.ef_t5yn_santillanvalles_klinsmann

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ef_t5yn_santillanvalles_klinsmann.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtEmail: TextInputEditText = binding.txtEmail
        val txtPassword: TextInputEditText = binding.etPassword

        binding.btnIngresar.setOnClickListener {
            authenticateUser(txtEmail.text.toString(), txtPassword.text.toString())
        }

        binding.btnRegistrar.setOnClickListener {
            createUser(txtEmail.text.toString(), txtPassword.text.toString())
        }
    }

    private fun authenticateUser(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startListaProductosActivity()
                    } else {
                        showToast(task.exception?.message ?: "Authentication failed.")
                    }
                }
                .addOnFailureListener {
                    showToast(it.message ?: "Authentication failed.")
                }
        } else {
            showToast("Debe completar todos los datos")
        }
    }

    private fun createUser(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startListaProductosActivity()
                    } else {
                        val errorMessage = task.exception?.message ?: "Registration failed."
                        if (errorMessage.contains("email address is already in use")) {
                            showToast("El usuario ya existe.")
                        } else {
                            showToast(errorMessage)
                        }
                    }
                }
                .addOnFailureListener {
                    showToast(it.message ?: "Registration failed.")
                }
        } else {
            showToast("Debe completar todos los datos")
        }
    }

    private fun startListaProductosActivity() {
        val intent = Intent(this, ListaProductosActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
