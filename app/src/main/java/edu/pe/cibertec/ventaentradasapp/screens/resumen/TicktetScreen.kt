package edu.pe.cibertec.ventaentradasapp.screens.resumen

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
import coil.compose.AsyncImage

@Composable
fun TicketScreen (modifier: Modifier){
    Column (modifier= Modifier.fillMaxSize().
        padding(15.dp).
        verticalScroll(
            rememberScrollState()
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "TICKET ENTRADA",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(bottom = 20.dp)
        )

        Column (
            modifier = Modifier.fillMaxWidth().
            padding(vertical = 5.dp)
        )
        {
            Row ( modifier = Modifier.fillMaxWidth().
            padding(vertical = 5.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Pelicula: ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    "NOMBRE DE LA PELICULA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Row ( modifier = Modifier.fillMaxWidth().
            padding(vertical = 5.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Pelicula: ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    "NOMBRE DE LA PELICULA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Row ( modifier = Modifier.fillMaxWidth().
            padding(vertical = 5.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Pelicula: ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    "NOMBRE DE LA PELICULA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Row ( modifier = Modifier.fillMaxWidth().
            padding(vertical = 5.dp),
                horizontalArrangement =  Arrangement.SpaceBetween
            ) {
                Text(
                    "Pelicula: ",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    "NOMBRE DE LA PELICULA",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

        }
        Spacer(modifier= Modifier.height(10.dp))
        AsyncImage(
            model = "https://www.revistaclinicacontemporanea.org/imagenes/Blade_Runner.jpg",
            contentDescription = "poster de pelicula",
            modifier = Modifier.width(200.dp). height(280.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id =  android.R.drawable.ic_menu_add),
            error = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel)
        )
        Spacer(modifier= Modifier.height(10.dp))

    }



}