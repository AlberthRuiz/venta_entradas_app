package edu.pe.cibertec.ventaentradasapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import edu.pe.cibertec.ventaentradasapp.screens.resumen.TicketScreen
import edu.pe.cibertec.ventaentradasapp.screens.venta.VentaScreen

@Composable
fun Navigation(modifier: Modifier, navHostController: NavHostController){
    NavHost(navController =  navHostController, startDestination = "venta")
    {
        composable("venta") {
            VentaScreen(modifier, navHostController)
        }
        composable("ticket") {
            TicketScreen(modifier)
        }
    }

}