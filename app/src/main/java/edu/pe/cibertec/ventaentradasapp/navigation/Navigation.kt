package edu.pe.cibertec.ventaentradasapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.pe.cibertec.ventaentradasapp.screens.resumen.TicketScreen
import edu.pe.cibertec.ventaentradasapp.screens.venta.VentaScreen

@Composable
fun Navigation(modifier: Modifier, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "venta")
    {
        composable("venta") {
            VentaScreen(modifier, navHostController)
        }
        composable(
            route = "ticket?nombre={nombre}&precio={precio}&sala={sala}&cantidad={cantidad}&incremento={incremento}&descuento={descuento}&precioTotal={precioTotal}&img_url={img_url}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("precio") {
                    type = NavType.FloatType
                },
                navArgument("sala") { type = NavType.StringType },
                navArgument("cantidad") { type = NavType.IntType },
                navArgument("incremento") { type = NavType.FloatType },
                navArgument("descuento") { type = NavType.FloatType },
                navArgument("precioTotal") { type = NavType.FloatType },
                navArgument("img_url") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val precio = backStackEntry.arguments?.getFloat("precio") ?: 0.0f
            val sala = backStackEntry.arguments?.getString("sala")
                ?: "" // Recuerda decodificar si es necesario (generalmente no para la recepción)
            val cantidad = backStackEntry.arguments?.getInt("cantidad") ?: 0
            val incremento = backStackEntry.arguments?.getFloat("incremento") ?: 0.0f
            val descuento = backStackEntry.arguments?.getFloat("descuento") ?: 0.0f
            val precioTotal = backStackEntry.arguments?.getFloat("precioTotal") ?: 0.0f
            val imgUrl = backStackEntry.arguments?.getString("img_url")
                ?: "" // Recuerda decodificar si es necesario

            TicketScreen(
                modifier = modifier,
                nombre = nombre,
                precio = precio.toDouble(), // Convierte a Double si es necesario
                sala = sala,
                cantidad = cantidad,
                incremento = incremento.toDouble(),
                descuento = descuento.toDouble(),
                precioTotal = precioTotal.toDouble(),
                imgUrl = imgUrl
            )
        }
    }

}