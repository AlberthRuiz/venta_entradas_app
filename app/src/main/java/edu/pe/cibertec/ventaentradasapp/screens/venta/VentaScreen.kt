package edu.pe.cibertec.ventaentradasapp.screens.venta

import android.text.Layout.Alignment
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.compareTo
import kotlin.times

enum class pelicula (
    val titulo: String,
    val precio: Double,
    val imagenUrl: String
){
    Alien("Alien Romulus", 30.00, "https://m.media-amazon.com/images/I/91R9N-YuPcL._SX342_.jpg"),
    DeadPoolAndWolverine("Deadpool y Wolverine", 25.00, "https://en.wikipedia.org/wiki/File:Deadpool_%26_Wolverine_poster.jpg"),
    Beetlejuice("Beetlejuice", 20.00, "https://en.wikipedia.org/wiki/File:Beetlejuice_Beetlejuice_poster.jpg"),
    BladeRunner2049("Blade Runner 2049", 14.50,"https://www.revistaclinicacontemporanea.org/imagenes/Blade_Runner.jpg"),
    Constantine("Constantine", 17.10,"https://resizing.flixster.com/mOxGhpYzFmKNsSc6ucumX_RBDVQ=/206x305/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p35545_p_v8_al.jpg"),

}




@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun VentaScreen(modifier: Modifier = Modifier, navController: NavController){

    val context = LocalContext.current

    val viewModel: VentaViewModel = remember { VentaViewModel() }

    var expanded by remember { mutableStateOf(false) }
    var seleccion by remember { mutableStateOf("") }
    val radiOptions = listOf(
        "Sala 1 - IMAX (20%)",
        "Sala 2 - Dolby Atmos (15%)",
        "Sala 3 - Space X (10%)"
    )
    var selectedOption by remember { mutableStateOf(radiOptions[0]) }

    var cant by remember {  mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ){
        Column (modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text= "VENTA DE ENTRADAS",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = Color.Magenta,

            )
            Spacer(Modifier.height(5.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = seleccion,
                    label = {
                        Text("Selecciona una pelicula!")
                    },
                    onValueChange = {
                        seleccion = it.toString()
                        viewModel.onChangeNombre(seleccion)

                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon( expanded =  expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded=false
                    }
                ) {
                    pelicula.values().forEach { m ->
                        DropdownMenuItem(
                            text = {
                                Text("${m.titulo}")
                            },
                            onClick = {
                                seleccion = "${m.titulo}"
                                viewModel.onChangeUrlImagen(m.imagenUrl)
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("Seleccione un sala: ",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            //Radio button Seleccion de sala
            radiOptions.forEach { option ->
                Row (verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    RadioButton(
                        selected =  (option == selectedOption),
                        onClick = {
                            selectedOption = option
                            viewModel.onChangeSala(option)
                        }
                    )
                    Text(option)
                }
            }
            Spacer(modifier= Modifier.height(10.dp))
            Row(
                modifier= Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            )
            {
                Text("Cantidad", modifier = Modifier.weight(1f))
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation.None,
                    value = cant.toString(),
                    onValueChange = {
                        cant = if (it.isNotEmpty()) it.toInt() else 0
                        viewModel.onChangeCantidad(cant)
                    },
                    label = {
                        Text("N°",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                            )
                    },
                    modifier =  Modifier.weight(2f)

                )
            }
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier.width(250.dp).align(androidx.compose.ui.Alignment.CenterHorizontally),
                onClick = {
                    if (viewModel.pelicula_seleccionada.value.cantidad == 0) {
                        Toast.makeText(context, "La cantidad no puede ser 0", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }
                    val selectedMovie = pelicula.values().find { it.titulo == seleccion }
                    if (selectedMovie != null) {
                        viewModel.onChangePrecio(selectedMovie.precio)
                    } else {
                        Toast.makeText(context, "Selecciona una pelicula", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }

                    val subtotal =
                        viewModel.pelicula_seleccionada.value.precio * viewModel.pelicula_seleccionada.value.cantidad


                    val incrementoPorcentaje = when (viewModel.pelicula_seleccionada.value.sala) {
                        "Sala 1 - IMAX (20%)" -> 0.20
                        "Sala 2 - Dolby Atmos (15%)" -> 0.15
                        "Sala 3 - Screen X (10%)" -> 0.10
                        else -> 0.0
                    }
                    val incremento = subtotal * incrementoPorcentaje

                    val descuentoPorcentaje = when {
                        viewModel.pelicula_seleccionada.value.cantidad >= 5 -> 0.15
                        viewModel.pelicula_seleccionada.value.cantidad >= 3 -> 0.10
                        else -> 0.0
                    }
                    val descuento = subtotal * descuentoPorcentaje


                    val total = subtotal + incremento - descuento

                    viewModel.onChangePrecio(subtotal)
                    viewModel.onChangeIncremento(incremento)
                    viewModel.onChangeDescuento(descuento)
                    viewModel.onChangePrecioTotal(total)

                },
            ) {
                Text("Agregar +")
            }

            Spacer(Modifier.height(10.dp))
            Column {
                Row(
                    Modifier.fillMaxWidth(). padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                )
                {
                    Text("Sub Total")
                    Text("Increm.")
                    Text("Dscto.")
                    Text("Total")

                }
                Row(
                    Modifier.fillMaxWidth(). padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                )
                {
                    Text("${viewModel.pelicula_seleccionada.value.precio}")
                    Text("${viewModel.pelicula_seleccionada.value.incremento}")
                    Text("${viewModel.pelicula_seleccionada.value.descuento}")
                    Text("${viewModel.pelicula_seleccionada.value.precio_total}")

                }
            }
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier.width(250.dp).align(androidx.compose.ui.Alignment.CenterHorizontally),
                onClick = {
                    val pelicula = viewModel.pelicula_seleccionada.value
                    val nombre = pelicula.nombre
                    val precio = pelicula.precio
                    val sala = pelicula.sala
                    val cantidad = pelicula.cantidad
                    val incremento = pelicula.incremento
                    val descuento = pelicula.descuento
                    val precioTotal = pelicula.precio_total
                    val urlImagen = pelicula.img_url
                    val encodedSala = URLEncoder.encode(sala, StandardCharsets.UTF_8.name())
                    val encodedUrlImagen =
                        URLEncoder.encode(urlImagen, StandardCharsets.UTF_8.name())
                    navController.navigate(
                        "ticket?nombre=$nombre&precio=$precio&sala=$encodedSala&cantidad=$cantidad&incremento=$incremento&descuento=$descuento&precioTotal=$precioTotal&img_url=$encodedUrlImagen"
                    )
                },
            ) {
                Text("Imprimir")
            }

        }

    }
}