package edu.pe.cibertec.ventaentradasapp.screens.venta

data class StatePelicula(
    var nombre:String,
    var precio:Double,
    var sala: String,
    var cantidad: Int,
    var incremento: Double,
    var descuento: Double,
    var precio_total: Double,
    var img_url: String
)
{
    constructor():
            this("",0.0,
                "", 0,0.0,
                0.0,0.0, "")
}