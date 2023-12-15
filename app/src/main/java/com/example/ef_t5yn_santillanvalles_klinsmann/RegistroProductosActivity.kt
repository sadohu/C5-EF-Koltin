package com.example.ef_t5yn_santillanvalles_klinsmann

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.ef_t5yn_santillanvalles_klinsmann.model.Producto
import java.util.Arrays

class RegistroProductosActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_productos)

        var indexMarcaProducto = -1
        val marcaProducto =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.arrayMarca)))
        val spinnerMarca = findViewById(R.id.cboMarca) as Spinner
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcaProducto)
        spinnerMarca.adapter = arrayAdapter

        spinnerMarca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                indexMarcaProducto = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var indexCategoriaProducto = -1
        val categoriaProducto =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.arrayCategoria)))
        val spinnerCategoria = findViewById(R.id.cboCategoria) as Spinner
        val arrayAdapter2 =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoriaProducto)
        spinnerCategoria.adapter = arrayAdapter2

        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                indexCategoriaProducto = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var indexUnidadDeMedida = -1
        val unidadProducto =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.arrayUnidadDeMedida)))
        val spinnerUnidad = findViewById(R.id.cboUnidadMedida) as Spinner
        val arrayAdapter3 =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unidadProducto)
        spinnerUnidad.adapter = arrayAdapter3

        spinnerUnidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                indexUnidadDeMedida = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnLimpiar.setOnClickListener {
            val txtProducto = findViewById<EditText>(R.id.txtProducto)
            val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
            val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
            val txtFoto = findViewById<EditText>(R.id.txtFoto)
            val txtStock = findViewById<EditText>(R.id.txtStock)
            val txtCantidadUni = findViewById<EditText>(R.id.txtCantidadUnitaria)
            val spinnerMarca = findViewById<Spinner>(R.id.cboMarca)
            val spinnerCategoria = findViewById<Spinner>(R.id.cboCategoria)
            val spinnerUnidad = findViewById<Spinner>(R.id.cboUnidadMedida)
            txtProducto.text.clear()
            txtDescripcion.text.clear()
            txtPrecio.text.clear()
            txtFoto.text.clear()
            txtStock.text.clear()
            txtCantidadUni.text.clear()
            spinnerMarca.setSelection(0)
            spinnerCategoria.setSelection(0)
            spinnerUnidad.setSelection(0)
        }

        val addProducto = findViewById(R.id.btnGuardar) as Button
        addProducto.setOnClickListener {
            val txtProducto = findViewById<EditText>(R.id.txtProducto)
            val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
            val cboMarca = indexMarcaProducto - 1
            val cboCategoria = indexCategoriaProducto - 1

            val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
            val precioString = txtPrecio.text.toString().trim()
            val precioDouble = if (precioString.isNotEmpty()) precioString.toDouble() else 0.0

            val txtFoto = findViewById<EditText>(R.id.txtFoto)

            val txtStock = findViewById<EditText>(R.id.txtStock)
            val stockString = txtStock.text.toString().trim()
            val stockInt = if (stockString.isNotEmpty()) stockString.toInt() else 0

            val txtCantidadUni = findViewById<EditText>(R.id.txtCantidadUnitaria)
            val cantidadString = txtCantidadUni.text.toString().trim()
            val cantidadInt = if (cantidadString.isNotEmpty()) cantidadString.toInt() else 0

            val cboUnidadDeMedida = indexUnidadDeMedida - 1

            if (txtProducto.text.isEmpty() || txtDescripcion.text.isEmpty() ||
                precioDouble <= 0 || txtFoto.text.isEmpty() ||
                stockInt <= 0 || cantidadInt <= 0 ||
                cboMarca < 0 || cboCategoria < 0 || cboUnidadDeMedida < 0
            ) {
                Toast.makeText(
                    this,
                    "Faltan completar campos o campos inválidos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val producto = Producto(
                    5,
                    txtProducto.text.toString().trim(),
                    txtDescripcion.text.toString().trim(),
                    cboMarca,
                    cboCategoria,
                    precioDouble,
                    txtFoto.text.toString().trim(),
                    stockInt,
                    cantidadInt,
                    cboUnidadDeMedida
                )
            }
        }
    }
}
