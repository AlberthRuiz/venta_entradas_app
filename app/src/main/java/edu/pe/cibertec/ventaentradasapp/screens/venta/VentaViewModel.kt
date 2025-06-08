package edu.pe.cibertec.ventaentradasapp.screens.venta

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class VentaViewModel {
    private val _pelicula_seleccionada = mutableStateOf(StatePelicula())
    val pelicula_seleccionada: State<StatePelicula> = _pelicula_seleccionada

    fun onChangeCantidad(cantidad: Int){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(cantidad = cantidad)
    }
    fun onChangePrecio(precio: Double){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(precio = precio)
    }
    fun onChangeNombre(nombre: String){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(nombre = nombre)
    }
    fun onChangeSala(sala: String){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(sala = sala)
    }
    fun onChangeIncremento(incremento: Double){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(incremento = incremento)
    }
    fun onChangeDescuento(descuento: Double){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(descuento = descuento)
    }
    fun onChangePrecioTotal(precio_total: Double){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(precio_total = precio_total)
    }
    fun onChangeUrlImagen(img_url: String){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(img_url = img_url)
    }



}