package edu.pe.cibertec.ventaentradasapp.screens.resumen

import android.os.Bundle
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun TicketScreen (modifier: Modifier, arguments: Bundle?){
    val nombre = arguments?.getString("nombre") ?: "XXXXXXX"
    val precio = arguments?.getDouble("precio") ?: 0.0
    val sala = arguments?.getString("sala") ?: "YYYYYY"
    val cantidad = arguments?.getInt("cantidad") ?: 0
    val incremento = arguments?.getDouble("incremento") ?: 0.0
    val descuento = arguments?.getDouble("descuento") ?: 0.0
    val precio_total = arguments?.getDouble("precio_total") ?: 0.0
    val img_url = arguments?.getString("img_url") ?: ""



    Column (
        modifier= Modifier.fillMaxSize().
        background(Color(0xFFF5F5F5)).
        padding(20.dp).
        verticalScroll(
            rememberScrollState()
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "TICKET ENTRADA",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  Color(0xFF6B46C1),
            modifier= Modifier.padding(bottom = 20.dp)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 20.dp)

        )
        {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Pelicula: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    nombre,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Cantidad: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    cantidad.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Subtotal: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    precio.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Incremento: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    incremento.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Descuento: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    descuento.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Total: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    precio_total.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(Modifier.height(5.dp))

        }
        Spacer(modifier= Modifier.height(10.dp))
        AsyncImage(
            model = img_url,
            contentDescription = "poster de pelicula",
            modifier = Modifier.width(200.dp). height(280.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id =  android.R.drawable.ic_menu_add),
            error = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel)
        )
        Spacer(modifier= Modifier.height(10.dp))

    }



}