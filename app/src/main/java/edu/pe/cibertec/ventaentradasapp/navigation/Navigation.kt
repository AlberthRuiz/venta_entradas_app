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
    NavHost(navController =  navHostController, startDestination = "ticket")
    {
        composable(
            route = "venta") {
            VentaScreen(modifier, navHostController)
        }
        composable(
           route =  "ticket",
            arguments = listOf(
                navArgument ("nombre") { type = NavType.StringType },
                navArgument ("precio") { type = NavType.FloatType },
                navArgument ("precio") { type = NavType.FloatType },
            )
            ) {
            TicketScreen(modifier)
        }
    }

}