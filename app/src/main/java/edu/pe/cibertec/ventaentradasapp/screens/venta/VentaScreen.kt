package edu.pe.cibertec.ventaentradasapp.screens.venta

import android.text.Layout.Alignment
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

enum class pelicula (
    val titulo: String,
    val precio: Double,
    val imagenUrl: String
){
    Alien("Alien Romulus", 30.00, "https://m.media-amazon.com/images/I/91R9N-YuPcL._SX342_.jpg"),
    DeadPoolAndWolverine("Deadpool y Wolverine", 25.00, "https://en.wikipedia.org/wiki/File:Deadpool_%26_Wolverine_poster.jpg"),
    Beetlejuice("Beetlejuice", 20.00, "https://en.wikipedia.org/wiki/File:Beetlejuice_Beetlejuice_poster.jpg"),
    BladeRunner2049("Blade Runner 2049", 14.50,"https://www.revistaclinicacontemporanea.org/imagenes/Blade_Runner.jpg")
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
                                viewModel.onChanegeUrlImagen(m.imagenUrl)
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
                    if (viewModel.pelicula_seleccionada.value.cantidad==0){
                        Toast.makeText(
                            context,
                            "Cantdad no puede ser 0",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    val peliculaActual = pelicula.values().find { it.titulo == seleccion }

                    if(peliculaActual!= null){
                        viewModel.onChangePrecio(peliculaActual.precio)
                    }else{
                        Toast.makeText(
                            context,
                            "Pelicula no seleccionada",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    val subTotal =
                        viewModel.pelicula_seleccionada.value.precio *  viewModel.pelicula_seleccionada.value.cantidad

                    val proc_incremento = when (viewModel.pelicula_seleccionada.value.sala){
                        "Sala 1 - IMAX (20%)" -> 0.20
                        "Sala 2 - Dolby Atmos (15%)" -> 0.15
                        "Sala 3 - Space X (10%)" -> 0.10
                        else ->  0.0
                    }
                    val incremento = subTotal * proc_incremento

                    val porc_descto = when
                    {
                        viewModel.pelicula_seleccionada.value.cantidad>=7 -> 0.15
                        viewModel.pelicula_seleccionada.value.cantidad>=5 -> 0.10
                        viewModel.pelicula_seleccionada.value.cantidad>=10 -> 0.25
                        else -> 0.0
                    }

                    val descto = subTotal * porc_descto

                    val total = subTotal + incremento + descto

                    viewModel.onChangeIncremento(incremento)
                    viewModel.onChangeDescuento(descto)
                    viewModel.onChangePrecioTotal(total)
                    viewModel.onChangeNombre(seleccion)

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
                    val p = viewModel.pelicula_seleccionada.value
                    val nombre = p.nombre
                    val precio = p.precio
                    val sala = p.sala
                    val cantidad = p.cantidad
                    val incremento = p.incremento
                    val descuento = p.descuento
                    val precio_total = p.precio_total
                    val img_url = p.img_url
                    val encodeSala = URLEncoder.encode(sala, StandardCharsets.UTF_8.name())

                    val encodeURL = URLEncoder.encode(img_url, StandardCharsets.UTF_8.name())

                    navController.navigate(
                        "ticket?" +
                                "nombre=$nombre&" +
                                "precio=$precio&" +
                                "sala=$encodeSala&" +
                                "cantidad=$cantidad&" +
                                "incremento=$incremento&" +
                                "descuento=$descuento&" +
                                "precio_total=$precio_total&" +
                                "img_url=$encodeURL",
                    )
                },
            ) {
                Text("Imprimir")
            }

        }

    }
}