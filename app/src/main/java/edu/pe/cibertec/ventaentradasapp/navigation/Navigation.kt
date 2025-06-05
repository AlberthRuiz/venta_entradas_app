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
fun Navigation(modifier: Modifier, navHostController: NavHostController){
    NavHost(navController =  navHostController, startDestination = "venta")
    {
        composable(
            route = "venta") {
            VentaScreen(modifier, navHostController)
        }
        composable(
           route =  "ticket?" +
                   "nombre={nombre}&" +
                   "precio={precio}&" +
                   "sala={sala}&" +
                   "cantidad={cantidad}&" +
                   "incremento={incremento}&" +
                   "descuento={descuento}&" +
                   "precio_total={precio_total}&" +
                   "img_url={img_url}",
            arguments = listOf(
                navArgument ("nombre") { type = NavType.StringType },
                navArgument ("precio") { type = NavType.FloatType },
                navArgument ("sala") { type = NavType.StringType },
                navArgument ("cantidad") { type = NavType.IntType },
                navArgument ("incremento") { type = NavType.FloatType },
                navArgument ("descuento") { type = NavType.FloatType },
                navArgument("precio_total") { type = NavType.FloatType },
                navArgument ("img_url") { type = NavType.StringType },
            )
            ) {
                backStackEntry ->
                TicketScreen(modifier,backStackEntry.arguments)
        }
    }

}