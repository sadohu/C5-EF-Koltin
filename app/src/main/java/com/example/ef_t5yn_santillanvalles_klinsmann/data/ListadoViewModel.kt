package com.example.ef_t5yn_santillanvalles_klinsmann.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ef_t5yn_santillanvalles_klinsmann.domain.Producto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.lang.Exception

class ListadoViewModel : ViewModel(){
    private lateinit var firestore : FirebaseFirestore
    private val _getList = MutableLiveData<List<Producto>>()
    val getList : LiveData<List<Producto>> = _getList

    private fun getApiList() : MutableList<Producto>{
//        firestore = FirebaseFirestore.getInstance()
        val listProducto : MutableList<Producto> = ArrayList()

//        firestore.collection("Producto").get().addOnSuccessListener { list ->
//            for(item in list){
//                val nombre = item.getString("nombre")
//                val descripcion = item.getString("descripcion")
//                val img = item.getString("img")
//                val ubicacion = item.getGeoPoint("ubicacion")
//                var restaurante = Restaurante(nombre, descripcion, img, ubicacion)
//                listRestaurant.add(restaurante)
//            }
//            _getList.postValue(listProducto)
//        }
        return listProducto
    }

    fun getAll() = viewModelScope.launch {
        try {
            getApiList()
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}