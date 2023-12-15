package com.example.ef_t5yn_santillanvalles_klinsmann

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import java.util.Arrays

class agregaractivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        val db = FirebaseDatabase.getInstance().getReference("Productos")

        var indexMarca = -1
        var indexCategoria = -1
        var indexUnidadMedida = -1

        val spnMarca = findViewById(R.id.spnMarca) as Spinner
        val spnCategoria = findViewById(R.id.spnCategoria) as Spinner
        val spnUnidadMedida = findViewById(R.id.spnUnidadMedida) as Spinner

        val btnAgregar = findViewById(R.id.btnAgregar) as Button
        val btnCancelar = findViewById(R.id.btnCancelar) as Button

        val txtNombre = findViewById(R.id.txtNombre) as TextInputEditText
        val txtDescripcion = findViewById(R.id.txtDescripcion) as TextInputEditText
        val txtPrecio = findViewById(R.id.txtPrecio) as TextInputEditText
        val txtFoto = findViewById(R.id.txtFoto) as TextInputEditText
        val txtStock = findViewById(R.id.txtStock) as TextInputEditText
        val txtCantidadUnit = findViewById(R.id.txtCantUnit) as TextInputEditText

        // Método para cargar la data a los spinners
        loadData(spnMarca, spnCategoria, spnUnidadMedida)

        btnCancelar.setOnClickListener {
            // Restablecer todos los controles
            loadData(spnMarca, spnCategoria, spnUnidadMedida)
            txtNombre.setText("")
            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtFoto.setText("")
            txtStock.setText("")
            txtCantidadUnit.setText("")
        }

        spnMarca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                indexMarca = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spnCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                indexCategoria = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spnUnidadMedida.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                indexUnidadMedida = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val extras = intent.extras
        if (extras != null) {

            val maxProductoId = extras.getInt("maxProductoId") + 1

            btnAgregar.setOnClickListener {

                val nombre = txtNombre.text.toString().trim()
                val descripcion = txtDescripcion.text.toString().trim()
                val foto = txtFoto.text.toString().trim()

                val precio = if (txtPrecio.text.toString() == "") 0 else Integer.parseInt(
                    txtPrecio.text.toString().trim()
                )
                val stock = if (txtStock.text.toString() == "") 0 else Integer.parseInt(
                    txtStock.text.toString().trim()
                )
                val cantidadUnitaria =
                    if (txtCantidadUnit.text.toString() == "") 0 else Integer.parseInt(
                        txtCantidadUnit.text.toString().trim()
                    )

                fun validateData(): Boolean {
                    if (indexMarca <= 0 || indexCategoria <= 0 || indexUnidadMedida <= 0 ||
                        nombre == "" || descripcion == "" ||
                        precio <= 0 || stock <= 0 || cantidadUnitaria <= 0
                    )
                        return false
                    return true
                }

                if (!validateData()) {
                    Toast.makeText(this, "Debe completar los datos", Toast.LENGTH_LONG).show()
                } else {
                    // Agregar el producto
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setMessage("¿Estás seguro que deseas agregar ese producto?")
                        .setCancelable(false)
                        .setPositiveButton(
                            "Si",
                            DialogInterface.OnClickListener { dialogInterface, i ->

                                val objectId = db.push().key!!

                                val product = Producto(
                                    productoId = maxProductoId,
                                    nombre = nombre,
                                    precio = precio.toDouble(),
                                    cantidadUnitaria = cantidadUnitaria,
                                    marca = indexMarca,
                                    categoria = indexCategoria,
                                    descripcion = descripcion,
                                    stock = stock,
                                    foto = foto,
                                    unidadMedida = indexUnidadMedida,
                                    objectId = objectId
                                )

                                val loading =
                                    LoadingDialog(this@AgregarProductoActivity)
                                loading.startLoading()

                                val handler = Handler();
                                val intent = Intent(
                                    this@AgregarProductoActivity,
                                    ListadoProductoActivity::class.java
                                )
                                handler.postDelayed(object : Runnable {
                                    override fun run() {
                                        db.child(objectId).setValue(product)
                                            .addOnCompleteListener {
                                                Toast.makeText(
                                                    this@AgregarProductoActivity,
                                                    "Producto agregado correctamente",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                loading.dismissLoading()
                                                startActivity(intent)
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(
                                                    this@AgregarProductoActivity,
                                                    it.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    }
                                }, 2000)
                            })
                        .setNegativeButton(
                            "No agregar",
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                dialogInterface.cancel()
                            })
                    alertDialog.create().show()

                }
            }
        }
    }

    fun loadData(spnMarca: Spinner, spnCategoria: Spinner, spnUnidadMedida: Spinner) {
        val categoriaLst =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.listCategoria)))
        val marcaLst =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.listMarca)))
        val unidadMedidaLst =
            ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.listUnidadMedida)))

        var arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoriaLst)
        spnCategoria.adapter = arrayAdapter

        arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcaLst)
        spnMarca.adapter = arrayAdapter

        arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unidadMedidaLst)
        spnUnidadMedida.adapter = arrayAdapter
    }

}