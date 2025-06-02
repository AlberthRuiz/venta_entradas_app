package edu.pe.cibertec.ventaentradasapp.screens.venta

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class VentaViewModel {
    private val _pelicula_seleccionada = mutableStateOf(StatePelicula())
    val pelicula_seleccionada : State<StatePelicula> = _pelicula_seleccionada

    fun onChangePrecio(precio: Double){
        _pelicula_seleccionada.value = _pelicula_seleccionada.value.copy(precio =precio)
    }

}